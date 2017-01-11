var mongoose = require('mongoose');

//Room Schema
var roomSchema = mongoose.Schema({
	_ma:{
		type: String,
		required: true
	},
	_checkIn:{
		type: String
	},
	_checkOut:{
		type: String
	},
	_adultNum: {
		type: Number
	},
	_childrenNum: {
		type: Number
	},
	_price: {
		type: Number
	}
});


var Room = mongoose.model('Room', roomSchema);

module.exports = Room;