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


var port = process.env.PORT || 3000;
app.listen(port);