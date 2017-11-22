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

import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author informatica
 *
 */
public class MHHospitalization extends X_H_Hospitalization {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MHHospitalization f_Hospitalization_ID;
	/**
	 * @param ctx
	 * @param H_Hospitalization_ID
	 * @param trxName
	 */
	public MHHospitalization(Properties ctx, int H_Hospitalization_ID, String trxName) {
		super(ctx, H_Hospitalization_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MHHospitalization(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public static List<MHHospitalization> getByID(Properties ctx, int H_Hospitalization_ID, String trxName) {
		final String whereClause = "H_Hospitalization_ID = ?";
		Query q = new Query (ctx, "H_Hospitalization", whereClause, trxName);
		q.setParameters(H_Hospitalization_ID);
		return (q.list());
		
		
	}

	public static List<MHHospitalization> getByDateFinish(Properties ctx, Timestamp DateFinish, String trxName) {
		final String whereClause = "DateFinish <= ?";
//		final String whereClause = "DateFinish <= ? AND H_Bed_ID NOT IN ( SELECT H_Bed_ID FROM H_Hospitalization WHERE DateFinish >= ? )";
		Query q = new Query (ctx, "H_Hospitalization", whereClause, trxName);
		List<Timestamp> list = new ArrayList<Timestamp>();
		list.add(DateFinish);
		list.add(DateFinish);
		q.setParameters(list);
		return (q.list());
		
		
	}
	
	public static MHHospitalization[] getDate(Timestamp p_date) {
		MHHospitalization[] m_Hospitalization;
		ArrayList<MHHospitalization> list = new ArrayList<MHHospitalization>();
		final String sql = "SELECT * FROM H_Hospitalization WHERE DateFinish <= ? AND H_Bed_ID NOT IN ( SELECT H_Bed_ID FROM H_Hospitalization WHERE DateStart >= ? )";
//		final String sql = "SELECT * FROM H_Hospitalization WHERE DateFinish <= ? AND H_Bed_ID NOT IN ( SELECT H_Bed_ID FROM H_Hospitalization WHERE DateFinish > ? )";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setTimestamp(1, p_date);
			pstmt.setTimestamp(2, p_date);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MHHospitalization (Env.getCtx(), rs, null));
		}
		catch (Exception e)
		{
//			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

		m_Hospitalization = new MHHospitalization[list.size()];
		list.toArray(m_Hospitalization);
		return m_Hospitalization;
	}
	
	/* (non-Javadoc)
	 * @see org.compiere.model.PO#beforeSave(boolean)
	 */
	@Override
	protected boolean beforeSave(boolean newRecord) {
/**
 * Version 1
		
		int m_Hospitalization_ID = getH_Hospitalization_ID();
		
		if (m_Hospitalization_ID != 0) {
			List<MHHospitalization> f_Hospitalization_ID = getByID(getCtx(), m_Hospitalization_ID, get_TrxName());

			if (!f_Hospitalization_ID.isEmpty()) {
				int m_Query_ID = getH_Query_ID();
				int f_Query_ID = f_Hospitalization_ID.get(0).getH_Query_ID();
				
				if (m_Query_ID != f_Query_ID ) {
					MHQuery mQuery = new MHQuery(getCtx(), f_Query_ID, get_TrxName());
					mQuery.setIsAttended(false);
					mQuery.saveEx();
				}
	
				int m_Bed_ID = getH_Bed_ID();
				int f_Bed_ID = f_Hospitalization_ID.get(0).getH_Bed_ID();
				if (m_Bed_ID != f_Bed_ID ) {
					MHBed mBed = new MHBed(getCtx(), f_Bed_ID, get_TrxName());
					mBed.set_CustomColumn("IsAvailable", "Y");
					mBed.saveEx();
				}
			}
		}
*/

/**
 * Version 2
 */
		int m_Hospitalization_ID = getH_Hospitalization_ID();
		
		if (m_Hospitalization_ID != 0) {
			f_Hospitalization_ID = new MHHospitalization(getCtx(), m_Hospitalization_ID, get_TrxName());
		}
/**
 * Version 2
 */
		
		return super.beforeSave(newRecord);
	}
	
	/* (non-Javadoc)
	 * @see org.compiere.model.PO#afterSave(boolean, boolean)
	 */
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		MHBed mBed;
/**
 * Version 2
 */
		if (f_Hospitalization_ID != null) {
			int a_Query_ID = getH_Query_ID();
			int f_Query_ID = f_Hospitalization_ID.getH_Query_ID();
			
			if (a_Query_ID != f_Query_ID ) {
				MHQuery aQuery = new MHQuery(getCtx(), f_Query_ID, get_TrxName());
				aQuery.setIsAttended(false);
				aQuery.saveEx();
			}

			int a_Bed_ID = getH_Bed_ID();
			int f_Bed_ID = f_Hospitalization_ID.getH_Bed_ID();
			if (a_Bed_ID != f_Bed_ID ) {
				mBed = new MHBed(getCtx(), f_Bed_ID, get_TrxName());
				mBed.setIsAvailable(true);
				mBed.saveEx();
			}
		}
/**
 * Version 2
 */		
		
/**
 * Version 3 - Raul
 */
/*
		if (get_ValueOld(COLUMNNAME_H_Bed_ID) != null) {
			int f_Bed_ID = (int)get_ValueOld(COLUMNNAME_H_Bed_ID);
			
			if (f_Bed_ID != getH_Bed_ID()) {
				mBed = new MHBed(getCtx(), f_Bed_ID, get_TrxName());
				mBed.setIsAvailable(true);
				mBed.saveEx();
			}
		}
*/
/**
 * Version 3 - Raul
 */
		int m_Query_ID = getH_Query_ID();
		MHQuery mQuery = new MHQuery(getCtx(), m_Query_ID, get_TrxName());
		mQuery.set_CustomColumn("IsAttended", "Y");
		mQuery.saveEx();

		int m_Bed_ID = getH_Bed_ID();
		mBed = new MHBed(getCtx(), m_Bed_ID, get_TrxName());
		mBed.set_CustomColumn("IsAvailable", "N");
		mBed.saveEx();

		return super.afterSave(newRecord, success);
	}

	/* (non-Javadoc)
	 * @see org.compiere.model.PO#afterDelete(boolean)
	 */
	@Override
	protected boolean afterDelete(boolean success) {
		int m_Query_ID = getH_Query_ID();
		MHQuery mQuery = new MHQuery(getCtx(), m_Query_ID, get_TrxName());
		mQuery.set_CustomColumn("IsAttended", "N");
		mQuery.saveEx();

		int m_Bed_ID = getH_Bed_ID();
		MHBed mBed = new MHBed(getCtx(), m_Bed_ID, get_TrxName());
		mBed.set_CustomColumn("IsAvailable", "Y");
		mBed.saveEx();
		
		return super.afterDelete(success);
	}

}
