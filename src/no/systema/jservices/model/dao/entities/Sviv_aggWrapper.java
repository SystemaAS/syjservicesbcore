package no.systema.jservices.model.dao.entities;

import java.util.Collection;
import java.util.List;

import lombok.Data;

@Data
public class Sviv_aggWrapper {

	private Collection<Sviv_aggDao> dataAggr;
	private Collection<SvivRflnDao> dataRfln;
	private Collection<Sviva_aggDao> dataAggrAvg;
	private Collection<SvivRflnDao> dataSviv;
	
	
	
}
