//==============================================================================
// Brief   : SEQUENCE(LINK_ID, LINK_STATUS_RSP) Data Type
// Authors : Carlos Guimarães <cguimaraes@av.it.pt>
//------------------------------------------------------------------------------
// ODTONE - Open Dot Twenty One
//
// Copyright (C) 2009-2013 Universidade Aveiro
// Copyright (C) 2009-2013 Instituto de Telecomunicações - Pólo Aveiro
//
// This software is distributed under a license. The full license
// agreement can be found in the file LICENSE in this distribution.
// This software may not be copied, modified, sold or distributed
// other than expressed in the named license agreement.
//
// This software is distributed without any warranty.
//==============================================================================

package mih.types.links.auxiliar;

import mih.types.Serializeable;
import mih.types.basic.Sequence;
import mih.types.links.LinkId;
import mih.types.links.LinkStatusRsp;

public class LinkIdLinkStatusRspSequence
		extends
		Sequence<LinkId, LinkStatusRsp, Sequence.ObjectNull, Sequence.ObjectNull, Sequence.ObjectNull, Sequence.ObjectNull, Sequence.ObjectNull, Sequence.ObjectNull, Sequence.ObjectNull, Sequence.ObjectNull, Sequence.ObjectNull>
		implements Serializeable {

	public LinkIdLinkStatusRspSequence(LinkId linkId, LinkStatusRsp linkStatusRsp) {
		super(linkId, linkStatusRsp);
	}

	public LinkIdLinkStatusRspSequence(LinkIdLinkStatusRspSequence value) {
		super(value.getLinkId(), value.getLinkStatusRsp());
	}

	public LinkIdLinkStatusRspSequence() {
		super(new LinkId(), new LinkStatusRsp());
	}

	public LinkId getLinkId() {
		return getA();
	}

	public void setLinkId(LinkId linkId) {
		setA(linkId);
	}

	public LinkStatusRsp getLinkStatusRsp() {
		return getB();
	}

	public void setLinkStatusRsp(LinkStatusRsp linkStatusRsp) {
		setB(linkStatusRsp);
	}
}
