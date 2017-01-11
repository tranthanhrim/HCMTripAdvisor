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
app.get('/details', function(req, res) {
	var detailSearch = req.query.detailSearch;
	Detail.find({
        _ma: detailSearch
    }).select().exec(function(err, details) {
        if (err) {
            return res.status(404).send('Not found');
            console.log('Failed!!');
        } else {
            res.status(200).send(details);
            console.log(details);
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
        _avatar: req.body.avatar
    });

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
});

//DELETE
apiRoutes.delete('/users/:ma', function(req, res) {
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
apiRoutes.put('/users/:ma', function(req, res) {

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


var port = process.env.PORT || 3000;
app.listen(port);