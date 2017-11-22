/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                          *
 * This program is free software; you can redistribute it and/or modify it           *
 * under the terms version 2 of the GNU General Public License as published          *
 * by the Free Software Foundation. This program is distributed in the hope          *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied        *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                  *
 * See the GNU General Public License for more details.                              *
 * You should have received a copy of the GNU General Public License along           *
 * with this program; if not, write to the Free Software Foundation, Inc.,           *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                            *
 * For the text or an alternative of this public license, you may reach us           *
 * Copyright (C) 2017-2017 Las Plumas y Asociados, C.A. All Rights Reserved. 	     *
 * Contributor(s): German Laguna www.grupoplumas.net                       *
 *************************************************************************************/
package org.curso.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author German Laguna
 *
 */
public class MHAffliction extends X_H_Affliction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * *** Constructor de la Clase ***
	 * @author German Laguna 8 nov. 2017, 15:00:33
	 * @param ctx
	 * @param H_Affliction_ID
	 * @param trxName
	 */
	public MHAffliction(Properties ctx, int H_Affliction_ID, String trxName) {
		super(ctx, H_Affliction_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * *** Constructor de la Clase ***
	 * @author German Laguna 8 nov. 2017, 15:00:33
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MHAffliction(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}