var mongoose = require('mongoose');

//HCM Schema
var hcmSchema = mongoose.Schema({
	_overView:{
		type:String,
		required: true
	},
	_image:{
		type:Array,
		required: true
	},
	_thing:{
		type:Array
	},
	_specialFood: {
		type: Array
	},
	_Advise: {
		type: Array
	}
});


var HCM = mongoose.model('HCM',hcmSchema);

module.exports = HCM;