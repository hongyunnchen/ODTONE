//==============================================================================
// Brief   : INTEGER(2) Data Type
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

package mih.types.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import mih.types.Serializeable;

public class Integer16 implements Serializeable {

	public Integer16(int value) {
		this.value = (short) (value & 0xffff);
	}

	public Integer16(Integer16 value) {
		this.value = value.value;
	}

	public Integer16() {
		this.value = 0;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = (short) (value & 0xffff);
	}

	public void toBytes(ByteArrayOutputStream stream) {
		stream.write((value >> 8) & 0xff);
		stream.write( value       & 0xff);
	}

	public void fromBytes(ByteArrayInputStream stream) {
		value = 0;
		value |= stream.read() << 8;
		value |= stream.read();
	}

	private short value;
}