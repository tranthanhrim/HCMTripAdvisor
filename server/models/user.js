var mongoose = require('mongoose');

//User Schema
var userSchema = mongoose.Schema({
	_ma:{
		type:String,
		required: true
	},
	_userName:{
		type:String,
		required: true
	},
	_email:{
		type:String,
		required: true
	},
	_reviewID: {
		type: Number
	},
	_rateID: {
		type: Number
	}
});


var User = mongoose.model('User',userSchema);

module.exports = User;