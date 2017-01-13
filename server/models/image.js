var mongoose = require('mongoose');

//Image Schema
var imageSchema = mongoose.Schema({
	_ma:{
		type: String,
		required: true
	},
	_link:{
		type: Array
	}
});


var Image = mongoose.model('Image', imageSchema);

module.exports = Image;