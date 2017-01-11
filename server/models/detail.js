var mongoose = require('mongoose');

//Detail Schema
var detailSchema = mongoose.Schema({
	_ma: {
		type: String,
		require: true
	},
	_ratingSummary:{
		type: Number
	},
	_feature:{
		type: Array
	},
	_goodFor: {
		type: Array
	},
	_openHour: {
		type: Array
	},
	_location: {
		type: String
	},
	_phoneNumber: {
		type: Number
	},
	_description: {
		type: String
	},
	_review: {
		type: Array
	}
});


var Detail= mongoose.model('Detail',detailSchema);

module.exports = Detail;