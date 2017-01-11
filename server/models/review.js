var mongoose = require('mongoose');

//Review Schema
var reviewSchema = mongoose.Schema({
	_ma:{
		type: Number,
		require: true
	},
	_content: {
		type: String
	}
});


var Review= mongoose.model('Review', reviewSchema);

module.exports = Review;