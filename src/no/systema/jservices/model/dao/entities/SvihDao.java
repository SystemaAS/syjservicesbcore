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
}
