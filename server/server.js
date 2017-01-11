var express = require('express');
var app = express();
var bodyParser = require('body-parser');
app.use(bodyParser.json()); 

var ObjectId = require('mongodb').ObjectID;
var mongoose = require('mongoose');
mongoose.connect('mongodb://dungdinh:tthuyddung218@ds127968.mlab.com:27968/mobile_rest_api');
//ds127968.mlab.com:27968/mobile_rest_api
//ds129028.mlab.com:29028/mobileapi


var HCM = require('./models/HCM');
var User = require('./models/User');
var ThingsToDo = require('./models/thingstodo');
var Hotel = require('./models/Hotel');
var Room = require('./models/Room');
var Detail = require('./models/Detail');
var Review = require('./models/Review');
var Image = require('./models/Image');

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

var port = process.env.PORT || 3000;
app.listen(port);