var mongoose = require('mongoose');

//Review Schema
var ratingSchema = mongoose.Schema({
	_ma:{
		type: Number,
		require: true
	},
	_rate: {
		type: Number
	}
});


var RatingSummary = mongoose.model('RatingSummary', ratingSchema);

module.exports = RatingSummary;