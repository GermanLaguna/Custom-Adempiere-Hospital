/**
 * 
 */
package org.curso.process;

import java.util.List;

import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Msg;
import org.curso.model.I_H_Bed;
import org.curso.model.MHBed;

/**
 * @author informatica
 *
 */
public class SBReleaseBed extends SvrProcess {
	
	private List<MHBed> m_records = null;

	/**
	 * 
	 */
	public SBReleaseBed() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.SvrProcess#prepare()
	 */
	@Override
	protected void prepare() {

	}

	/* (non-Javadoc)
	 * @see org.compiere.process.SvrProcess#doIt()
	 */
	@Override
	protected String doIt() throws Exception {
		
//		MHHospitalization[] hospitalizations = MHHospitalization.getDate(p_DateFinish);
//		
//		int qty = 0;
//		for (int i = 0; i < hospitalizations.length; i++) {
//			int m_Bed_ID =  hospitalizations[i].getH_Bed_ID();
//			
//			MHBed mBed = new MHBed(getCtx(), m_Bed_ID, get_TrxName());
//			mBed.setIsAvailable(true);
//			mBed.saveEx();
//				
//			qty++;
//		}
//
//		return Msg.getMsg(getCtx(), "Release Bed") + " " + qty;
		
		int qty = 0;
		for (MHBed mBed : getBedRecords()) {
			mBed.setIsAvailable(true);
			mBed.saveEx();
			
			qty++;
		}
		
		return Msg.getMsg(getCtx(), "Release Bed") + " " + qty;
//		return null;
	}
	
	private List<MHBed> getBedRecords(){
		if (m_records != null) {
			return m_records;
		}
		
		String whereClause = "H_Bed_ID IN (SELECT T_Selection_ID FROM T_Selection WHERE T_Selection.AD_PInstance_ID = ?)";
//		String whereClause = "EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE T_Selection_ID = H_Bed_ID AND T_Selection.AD_PInstance_ID = ?)";
//		m_records = new Query(getCtx(), I_H_Bed.Table_Name, whereClause, get_TrxName()).setClient_ID().setParameters(getAD_PInstance_ID()).list();
		m_records = new Query(getCtx(), I_H_Bed.Table_Name, whereClause, get_TrxName()).setParameters(getAD_PInstance_ID()).list();
		
		return m_records;
	}

}