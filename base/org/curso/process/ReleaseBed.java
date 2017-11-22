/**
 * 
 */
package org.curso.process;

import java.sql.Timestamp;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Msg;
import org.curso.model.MHBed;
import org.curso.model.MHHospitalization;

/**
 * @author informatica
 *
 */
public class ReleaseBed extends SvrProcess {
	
	private Timestamp p_DateFinish;

	/**
	 * 
	 */
	public ReleaseBed() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.SvrProcess#prepare()
	 */
	@Override
	protected void prepare() {
		ProcessInfoParameter[] parameter = getParameter();
		
		for (int i = 0; i < parameter.length; ++i) {
			String name = parameter[i].getParameterName();
			if (name == null)
				;
			else if (name.equals("DateFinish")) {
				p_DateFinish = parameter[i].getParameterAsTimestamp();
			}
		}


	}

	/* (non-Javadoc)
	 * @see org.compiere.process.SvrProcess#doIt()
	 */
	@Override
	protected String doIt() throws Exception {

//		List<MHHospitalization> hospitalizations = MHHospitalization.getByDateFinish(getCtx(), p_DateFinish, get_TrxName());
//		List<MHBed> beds = MHBed.getByAvailable(getCtx(), false, get_TrxName());
//		
//		int qty = 0;
//		for (int i = 0; i < hospitalizations.size(); i++) {
//			int m_Bed_ID =  hospitalizations.get(i).getH_Bed_ID();
//			
//			for (int b = 0; b < beds.size(); b++) {
//				if (!beds.get(b).isAvailable()) {
//					MHBed mBed = new MHBed(getCtx(), m_Bed_ID, get_TrxName());
//					mBed.setIsAvailable(true);
//					mBed.saveEx();
//				
//					qty++;
//				}
//			}
//		}
		
		MHHospitalization[] hospitalizations = MHHospitalization.getDate(p_DateFinish);
		
		int qty = 0;
		for (int i = 0; i < hospitalizations.length; i++) {
			int m_Bed_ID =  hospitalizations[i].getH_Bed_ID();
			
			MHBed mBed = new MHBed(getCtx(), m_Bed_ID, get_TrxName());
			mBed.setIsAvailable(true);
			mBed.saveEx();
				
			qty++;
		}

		return Msg.getMsg(getCtx(), "Release Bed") + " " + qty;
	}

}