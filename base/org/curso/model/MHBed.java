/**
 * 
 */
package org.curso.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author informatica
 *
 */
public class MHBed extends X_H_Bed {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param ctx
	 * @param H_Bed_ID
	 * @param trxName
	 */
	public MHBed(Properties ctx, int H_Bed_ID, String trxName) {
		super(ctx, H_Bed_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MHBed(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public static List<MHBed> getByAvailable(Properties ctx, boolean IsAvailable, String trxName) {
		final String whereClause = "IsAvailable = ?";
		Query q = new Query (ctx, "H_Bed", whereClause, trxName);
		q.setParameters(IsAvailable);
		return (q.list());
	}

}