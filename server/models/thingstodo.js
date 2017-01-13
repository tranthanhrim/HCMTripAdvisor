var mongoose = require('mongoose');

//ThingsToDo Schema
var thingsToDoSchema = mongoose.Schema({
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
		type: String
	},
	_image: {
		type: String
	},
	_grade: {
		type: Number
	},
	_thumnailLink: {
		type: String
	},
	_ratingSummary: {
		type: String
	},
	_map: {
		type: Object
	}
});


var ThingsToDo = mongoose.model('ThingsToDo', thingsToDoSchema);

module.exports = ThingsToDo;