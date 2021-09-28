package no.systema.jservices.model.dao.entities;
import java.io.Serializable;

import lombok.Data;

@Data
public class SvihDao implements Serializable, IDao {

	private String svih_syav = "";
	private String svih_syop = "";
	private String svih_syst = "";
	private String svih_sysg = "";
	private String svih_tuid = "";
	//
	private String svih_syst2 = "";//  tegn           3   Ny status för TESS (TEM, ING, INI, etc)
	private String svih_mrn = "";//  tegn            22   MRN
	private String svih_lrn = "";//  tegn            22   LRN (ersätter tuillid i framtiden)
}
