var express = require('express');
var app = express();
var bodyParser = require('body-parser');
app.use(bodyParser.json()); 

var ObjectId = require('mongodb').ObjectID;
var mongoose = require('mongoose');
mongoose.connect('mongodb://dungdinh:tthuyddung218@ds127968.mlab.com:27968/mobile_rest_api');
//ds127968.mlab.com:27968/mobile_rest_api
//ds129028.mlab.com:29028/mobileapi


var HCM = require('./models/hcm');
var User = require('./models/user');
var ThingsToDo = require('./models/thingstodo');
var Hotel = require('./models/hotel');
var Room = require('./models/room');
var Detail = require('./models/detail');
var Review = require('./models/review');
var Image = require('./models/image');
var RatingSummary = require('./models/ratingsummary');

//API gets all Things to do
app.get('/thingstodo', function(req, res) {
	ThingsToDo.find(function(err, things) {
    	if (err)
            return console.error(err);
        else {
            res.status(200).send(things);
            console.log(things);
        }
	});
});

//API gets images of an ID
app.get('/images', function(req, res) {
	var imageSearch = req.query.imageSearch;
	Image.find({
        _ma: imageSearch
    }).select().exec(function(err, images) {
        if (err) {
            return res.status(404).send('Not found');
            console.log('Failed!!');
        } else {
            res.status(200).send(images);
            console.log(images);
        }
    });
});

//API gets details of an ID's thingstodo
app.get('/details_id', function(req, res) {
	var detailSearch = req.query.detailSearch;
	Detail.find({
        _ma: detailSearch
    }).select().limit(1).exec(function(err, detail1) {
        if (err) {
            return res.status(404).send('Not found');
            console.log('Failed!!');
        } else {
            res.status(200).send(detail1);
            console.log(detail1);
        }
    });
});

//---------User API--------
//ADD
app.post('/users', function(req, res) {
    // create a sample user
    var user = new User({
        _ma: req.body.ma,
        _email: req.body.email,
        _userName: req.body.userName,
        _avatar: req.body.avatar,
        _reviewID: req.body.ma,
        _rateID: req.body.ma
    });


    User.find({
        _ma: user._ma
    }).select().exec(function(err, users) {
    	console.log(user);
        	if(users.length < 1)
        	{
        		// save the user
			    user.save(function(err) {
			        if (err) {
			            res.status(400).json({
			                'error': 'bad request'
			            });
			        }

			        console.log('User saved successfully');
			        res.status(201).send({
			            'message': 'user created'
			        });
			    });
        	}
    });
    
});

//DELETE
app.delete('/users/:ma', function(req, res) {
    User.remove({
        _ma: req.params.ma
    }, function(err) {
        if (!err) {
            console.log('remove user successfull');
        } else {
            console.log(error);
        }
    });
});

//EDIT
app.put('/users/:ma', function(req, res) {

    var _email = req.body.email;
    var _userName = req.body.userName;
    var _avatar = req.body.avatar;


    User.findOne({
        _ma: req.params.ma
    }, function(err, user) {

        if (err) throw err;

        if (user) {
            user._email = _email;
            user._userName = _userName;
            user._avatar = _avatar;
            user._rateID = _ma;
            user._reviewID = _ma;


            user.save(function(err, user1) {
                if (err) {
                    res.status(400).send({
                        'error': 'Bad request (The data is invalid)'
                    });
                    return console.error(err);
                } else {
                    User.find(function(err, users) {
                        res.status(200).send({
                            'messege': 'Updated'
                        });
                    });
                }
            });
        } else {
            res.status(404).send({
                'messege': 'Not found'
            });
        }
    });
});

//API gets thingstodo according Type attritude
app.get('/thingstodo_type', function(req, res) {
	console.log('----------Vo ham----------')
	var typeSearch = req.query.typeSearch;
	console.log('--------Type Search--------', typeSearch)
	ThingsToDo.find({
        _type: typeSearch
    }).select().exec(function(err, things) {
        if (err) {
            return res.status(404).send('Not found');
            console.log('Failed!!');
        } else {
            res.status(200).send(things);
            console.log(things);
        }
    });
});

//API gets thingstodo according Type trừ Hotel và Food
app.get('/thingstodo_destination', function(req, res) {
	ThingsToDo.find({
        _type: { $nin: ["Hotels", "FoodnDrink"] }
    }).select().exec(function(err, things) {
        if (err) {
            return res.status(404).send('Not found');
            console.log('Failed!!');
        } else {
            res.status(200).send(things);
            console.log(things);
        }
    });
});


//++API post rate
//Hàm cập nhật lại
app.post('/ratings', function(req, res) {
	var _idUser = req.body._idUser;
    var _idThing = req.body._idThing;
    var _rate = req.body._rate;
    var _time = new Date();

    // create a sample ratingsummary
    var ratingsummary = new RatingSummary({
    	_thingsToDoID: _idThing,
    	_rate: _rate,
    	_idUser: _idUser,
    	_time: _time
    });

   	//Lấy ma rating cao nhất đang có trong bảng RatingSummary
   	// var _maxID;
   	// RatingSummary.find({
    // }).select(_ma -_id).sort(1).limit(1).exec(function(err, rating1) {
    // 	ratingsummary._ma = rating1 + 1;
    // });

    User.findOne({_ma: _idUser}).exec(function(err, user1) {
    	var _getRateID = user1._rateID;
    	ratingsummary._ma = _getRateID;

    	// save the ratings
	    ratingsummary.save(function(err) {
	        if (err) {
	            res.status(400).json({
	                'error': 'bad request'
	            });
	        }

	        console.log('Rating saved successfully');
	        res.status(201).send({
	            'message': 'Rating created'
	        });
	    });

	    //Cập nhật lại điểm Rate trong ThingsToDo
		ThingsToDo.findOne({_ma: _idThing}).exec(function(err, rating1) {
			rating1._ratingCount = rating1._ratingCount +1;
	    	rating1._ratingSummary = (rating1._ratingSummary + _rate)/2;

	    	//Cập nhật lại cái ThingsToDo
	    	rating1.save(function(err) {

	    });
	    });
    });
    
});

//++API post comment
//Hàm cập nhật lại
app.post('/comments', function(req, res) {
	var _idUser = req.body._idUser;
    var _idThing = req.body._idThing;
    var _content = req.body._content;
    var _time = new Date();

    // create a sample ratingsummary
    var review = new Review({
    	_thingsToDoID: _idThing,
    	_content: _content,
    	_time: _time,
    	_idUser: _idUser
    });

    User.findOne({_ma: _idUser}).exec(function(err, user1) {
    	var _getReviewID = user1._reviewID;

    	review._ma = _getReviewID;
    	console.log(user1);
    	console.log(review._ma);

    	// save the comment
	    review.save(function(err) {
	        if (err) {
	            res.status(400).json({
	                'error': 'bad request'
	            });
	        }

	        console.log('Rating saved successfully');
	        res.status(201).send({
	            'message': 'Rating created'
	        });
	    });
    });
    
});

//Hàm tính khoảng cách
var countdistance = function (lat1, lon1, lat2, lon2, unit) {
	var radlat1 = Math.PI * lat1/180
	var radlat2 = Math.PI * lat2/180
	var theta = lon1-lon2
	var radtheta = Math.PI * theta/180
	var dist = Math.sin(radlat1) * Math.sin(radlat2) + Math.cos(radlat1) * Math.cos(radlat2) * Math.cos(radtheta);
	dist = Math.acos(dist)
	dist = dist * 180/Math.PI
	dist = dist * 60 * 1.1515
	if (unit=="K") { dist = dist * 1.609344 }
	if (unit=="N") { dist = dist * 0.8684 }
	return dist
}

//Lấy top 5 thingstodo
app.get('/topthingstodo', function(req, res) {
	ThingsToDo.find({
    }).sort({_ratingCount: 1}).limit(5).select().exec(function(err, things) {
        if (err) {
            return res.status(404).send('Not found');
            console.log('Failed!!');
        } else {
            res.status(200).send(things);
            console.log(things);
        }
    });
});

//API get các địa điểm gần nhất
app.get('/nearme', function(req, res) {
	var lon = req.query.lon;
	var lat = req.query.lat;

	ThingsToDo.find({
    }).exec(function(err, things) {
        if (err) {
            return res.status(404).send('Not found');
            console.log('Failed!!');
        } else {
        	var tmp_array = [];

        	for(var i=0; i< things.length; i++){
        		var object_tmp = {
	        		_idThing: null,
	        		_distance: null
	        	};
        		object_tmp._idThing = things[i]._ma;		
        		var distance = countdistance(lat, lon, things[i]._map.latitude, things[i]._map.longtitude, "K");
        		object_tmp._distance = distance;
        		tmp_array.push(object_tmp);
        	}

        	//sort lại
        	// for(var i=0; i<tmp_array.length; i++){
        	// 	for(var j=1; j<tmp_array.length-1; j++)
        	// 	{
        	// 		if(tmp_array[i]._distance > tmp_array[j]._distance)
        	// 		{
        	// 			var tmp_var;
        	// 			tmp_array[i] = tmp_var;
        	// 			tmp_var = tmp_array[j];
        	// 			tmp_array[j] = tmp_array[i];
        	// 		}
        	// 	}	
        	// }
        	tmp_array.sort(function(a, b) {
        		return a._distance > b._distance;
        	});
        	console.log(tmp_array);
            res.status(200).send(tmp_array);
            // console.log(things);
        }
    });
});

//API Lấy List comment của ThingsToDo
app.get('/comments', function(req, res) {
	var _idThing= req.query._idThing;
	Review.find({_thingsToDoID: _idThing
    }).sort({_time: 1}).select().exec(function(err, reviews) {
        if (err) {
            return res.status(404).send('Not found');
            console.log('Failed!!');
        } else {
            res.status(200).send(reviews);
            console.log(reviews);
        }
    });
});

//API gets rate counting
app.get('/ratescounting', function(req, res) {
	var _idThing = req.query._idThing;
	RatingSummary.find({_thingsToDoID: _idThing
    }).exec(function(err, reviews) {
        if (err) {
            return res.status(404).send('Not found');
            console.log('Failed!!');
        } else {
            res.status(200).send({count: reviews.length});
            console.log(reviews.length);
        }
    });
});

//Lay rate của 1 ng cho 1 thing
app.get('/personrating', function(req, res) {
	var _idThing = req.query._idThing;
	var _idUser = req.query._idUser;
	User.findOne({_ma: _idUser
    }).select('_rateID -_id').exec(function(err, rateID1) {
    	console.log('RATE ID1', rateID1._rateID);
        if (err) {
            return res.status(404).send('Not found1');
            console.log('Failed!!');
        } else {
        	console.log('rateID1._rateID', rateID1._rateID);
        	console.log('_idUser', _idUser);
        	
		    RatingSummary.findOne({_ma: rateID1._rateID, _thingsToDoID: _idThing
		    }).sort({_time: -1}).exec(function(err, rating) {
		        if (err) {
		            return res.status(404).send('Not found2');
		            console.log('Failed!!');
		        } else {
		        	console.log(rating);
		        	if(rating == null)
		        	{
		        		res.status(200).send({rate: -1});
		        	}
		        	else{
		            	res.status(200).send({rate: rating._rate});
		        	}
		        }
		    });
        }
    });
});

//API gets ratingsummary of a thing
app.get('/ratingsummary', function(req, res) {
	var _idThing = req.query._idThing;
	ThingsToDo.findOne({_ma: _idThing
    }).select('_ratingSummary -_id').exec(function(err, things) {
        if (err) {
            return res.status(404).send('Not found');
            console.log('Failed!!');
        } else {
            res.status(200).send(things);
        }
    });
});

var port = process.env.PORT || 3000;
app.listen(port);