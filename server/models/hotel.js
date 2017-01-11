var mongoose = require('mongoose');

//Hotel Schema
var hotelSchema = mongoose.Schema({
	_ma:{
		type:String,
		required: true
	},
	_type:{
		type:String,
		required: true
	},
	_placeName:{
		type:String,
		required: true
	},
	_detail: {
		type: Number
	},
	_image: {
		type: Array
	},
	_grade: {
		type: Number
	},
	_room: {
		type: Array
	}
});


var Hotel = mongoose.model('Hotel',hotelSchema);

module.exports = Hotel;