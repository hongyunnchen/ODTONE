//==============================================================================
// Brief   : Transmit
// Authors : Simao Reis <sreis@av.it.pt>
//------------------------------------------------------------------------------
// ODTONE - Open Dot Twenty One
//
// Copyright (C) 2009-2011 Universidade Aveiro
// Copyright (C) 2009-2011 Instituto de Telecomunicações - Pólo Aveiro
//
// This software is distributed under a license. The full license
// agreement can be found in the file LICENSE in this distribution.
// This software may not be copied, modified, sold or distributed
// other than expressed in the named license agreement.
//
// This software is distributed without any warranty.
//==============================================================================

///////////////////////////////////////////////////////////////////////////////
#include "transmit.hpp"
#include "utils.hpp"
#include "log.hpp"
///////////////////////////////////////////////////////////////////////////////

namespace odtone { namespace mihf {

/**
 * Transmit module constructor.
 *
 * @param io io_service.
 * @param user_abook user book module.
 * @param link_abook link book module.
 * @param msg_out output message.
 */
transmit::transmit(io_service &io,
		           user_book &user_abook,
		           link_book &link_abook,
		           message_out &msg_out)
	: _io(io),
	  _user_abook(user_abook),
	  _link_abook(link_abook),
	  _msg_out(msg_out)
{
}

/**
 * Send message to a local entity. If the output message destination is a peer
 * MIHF redirect it to the message_out module.
 *
 * @param msg output message.
 */
void transmit::operator()(meta_message_ptr& msg)
{
	// TODO: remove try catch
	try{
		// FIXME: Response shouldn't be send to MIH Users
		if(msg->opcode() != mih::operation::request) {
			user_entry user = _user_abook.get(msg->destination().to_string());
			utils::udp_send(_io, msg, user.ip.c_str(), user.port);
			ODTONE_LOG(1, "(transmit) sending local message to: ",
			    msg->destination().to_string(), " ", user.ip, " ", user.port);
		}
		else {
			link_entry link = _link_abook.get(msg->destination().to_string());
			utils::udp_send(_io, msg, link.ip.c_str(), link.port);
			ODTONE_LOG(1, "(transmit) sending local message to: ",
			    msg->destination().to_string(), " ", link.ip, " ", link.port);
		}
	} catch (...) {
		if(msg->opcode() == mih::operation::confirm)
		{
			msg->opcode(mih::operation::response);
		}
		ODTONE_LOG(1, "(transmit) forwarding to message_out");
		_msg_out(msg);
	}
}

} /* namespace mihf */ } /* namespace odtone */
