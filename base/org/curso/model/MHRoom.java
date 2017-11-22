/**
 * 
 */
package org.curso.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author informatica
 *
 */
public class MHRoom extends X_H_Room {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param ctx
	 * @param H_Room_ID
	 * @param trxName
	 */
	public MHRoom(Properties ctx, int H_Room_ID, String trxName) {
		super(ctx, H_Room_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MHRoom(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
