package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import dao.AddrDAO;
import dao.impl.AddrDAOImpl;

public class AddrDAOTest {
	
	AddrDAO adao = new AddrDAOImpl();
	
	@Test
	public void test() {
		Map<String,String> addr = new HashMap<>();
		addr.put("sNum","11");
		addr.put("lNum", "20");
		List<Map<String,String>> addrList = adao.selectAddrList(addr);
		assertEquals(10, addrList.size()); // ~일거야 (기대값, 비교대상) : 비교대상이 기대값인가?
	}
	
	@Test
	public void addrCountTest() {
//		int totalCnt = adao.selectTotalAddrCnt();
//		assertEquals(358425, totalCnt);
	}

}
