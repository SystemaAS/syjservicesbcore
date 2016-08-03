package no.systema.jservices.bcore.z.maintenance.model.dao.mapper;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.bcore.z.maintenance.model.dao.entities.KodtaDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  Aug 1, 2016
 * 
 */
public class KodtaMapper implements RowMapper {
	private static Logger logger = Logger.getLogger(KodtaMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	
    	KodtaDao dao = new KodtaDao();
    	dao.setKoaavd(rs.getString("koaavd"));
    	dao.setKoaknr(rs.getString("koaknr"));
    	dao.setKoabaer(rs.getString("koabaer"));
    	dao.setKoakon(rs.getString("koakon"));
    	dao.setKoafir(rs.getString("koafir"));
    	dao.setKoanvn(rs.getString("koanvn"));
    	dao.setKoaiat(rs.getString("koaiat"));
    	dao.setKoaia2(rs.getString("koaia2"));
    	dao.setKoaie(rs.getString("koaie"));
    	dao.setKoapos(rs.getString("koapos"));
    	dao.setKoalk(rs.getString("koalk"));
    	
    	dao.setNavsg(rs.getString("navsg"));
    	dao.setKsidnr(rs.getString("ksidnr"));
    	//TODO
    	
        return dao;
    }

}


