package com.brunosidad.ws.rest.infraestructure;

public class AutomEmailBodyData {
		
	public String robot;
	public String db;
	public String query;

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getRobot() {
		return robot;
	}

	public void setRobot(String robot) {
		this.robot = robot;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery() {
		if(db.contains("Banca")) {
			query="select \r\n" + 
		   	   		"		D.CY_CYCLE as Suite,\r\n" + 
		   	   		"		t.TS_USER_04 as Canal,\r\n" + 
		   	   		"		t.TS_USER_05 as Funcionalidad,\r\n" + 
		   	   		"		rn.RN_USER_02 as Framework,\r\n" + 
		   	   		"		convert(varchar,isnull(passed.Cantidad,0))+' ('+convert(varchar,cast((isnull(passed.Cantidad,0) * 100.0/tot.Cantidad)as decimal(18,2)))+'%)' as Passed,\r\n" + 
		   	   		"		convert(varchar,isnull(fail.Cantidad,0))+' ('+convert(varchar,cast((isnull(fail.Cantidad,0) * 100.0/tot.Cantidad)as decimal(18,2)))+'%)' as Failed,\r\n" + 
		   	   		"		isnull(passed.Cantidad,0) as cantPassed,\r\n" + 
		   	   		"		isnull(fail.Cantidad,0) as cantFailed,\r\n" + 
		   	   		"		isnull(other.Cantidad,0) as cantOtrosEstados,\r\n" + 
		   	   		"		tot.Cantidad as cantTotal\r\n" + 
		   	   		"		\r\n" + 
		   	   		"		from td.RUN rn  \r\n" + 
		   	   		"		\r\n" + 
		   	   		"		Left Join td.CYCLE (nolock)D on rn.RN_CYCLE_ID=D.CY_CYCLE_ID \r\n" + 
		   	   		"		left join td.TESTCYCL (nolock) inst on inst.TC_TESTCYCL_ID=rn.RN_TESTCYCL_ID\r\n" + 
		   	   		"		left join td.TEST (nolock) t on t.TS_TEST_ID=inst.TC_TEST_ID \r\n" + 
		   	   		"		left join (select D.CY_CYCLE_ID as Suite, COUNT(1) as Cantidad	from td.RUN rn	Left Join td.CYCLE (nolock)D on rn.RN_CYCLE_ID=D.CY_CYCLE_ID where  RN_USER_01 like '%Autom%' and rn.RN_USER_02 is not null and RN_STATUS='Passed' and rn.RN_USER_03 ='"+robot+"' group by D.CY_CYCLE_ID) passed on passed.Suite=d.CY_CYCLE_ID\r\n" + 
		   	   		"		left join (select D.CY_CYCLE_ID as Suite, COUNT(1) as Cantidad	from td.RUN rn	Left Join td.CYCLE (nolock)D on rn.RN_CYCLE_ID=D.CY_CYCLE_ID where  RN_USER_01 like '%Autom%' and rn.RN_USER_02 is not null and RN_STATUS='Failed' and rn.RN_USER_03 ='"+robot+"' group by D.CY_CYCLE_ID) fail on fail.Suite=d.CY_CYCLE_ID\r\n" + 
		   	   		"		left join (select D.CY_CYCLE_ID as Suite, COUNT(1) as Cantidad	from td.RUN rn	Left Join td.CYCLE (nolock)D on rn.RN_CYCLE_ID=D.CY_CYCLE_ID where  RN_USER_01 like '%Autom%' and rn.RN_USER_02 is not null and RN_STATUS not in ('Failed','Passed') and rn.RN_USER_03 ='"+robot+"' group by D.CY_CYCLE_ID) other on other.Suite=d.CY_CYCLE_ID\r\n" + 
		   	   		"		left join (select D.CY_CYCLE_ID as Suite, COUNT(1) as Cantidad	from td.RUN rn	Left Join td.CYCLE (nolock)D on rn.RN_CYCLE_ID=D.CY_CYCLE_ID where  RN_USER_01 like '%Autom%' and rn.RN_USER_02 is not null and rn.RN_USER_03 ='"+robot+"' group by D.CY_CYCLE_ID) tot on tot.Suite=d.CY_CYCLE_ID\r\n" + 
		   	   		"		\r\n" + 
		   	   		"		where  RN_USER_01 like '%Autom%' and rn.RN_USER_02 is not null and rn.RN_USER_03 ='"+robot+"' \r\n" + 
		   	   		"		\r\n" + 
		   	   		"		group by D.CY_CYCLE,rn.RN_USER_02,t.TS_USER_04,t.TS_USER_05,passed.Cantidad,fail.Cantidad,other.Cantidad,tot.Cantidad\r\n" + 
		   	   		"		order by t.TS_USER_04 desc";
		}
		else 
		{
			query="select D.CY_CYCLE as Suite, t.TS_USER_03 as Canal, t.TS_USER_01 as Funcionalidad, rn.RN_USER_02 as Framework, convert(varchar,isnull(passed.Cantidad,0))+' ('+convert(varchar,cast((isnull(passed.Cantidad,0) * 100.0/tot.Cantidad)as decimal(18,2)))+'%)' as Passed, convert(varchar,isnull(fail.Cantidad,0))+' ('+convert(varchar,cast((isnull(fail.Cantidad,0) * 100.0/tot.Cantidad)as decimal(18,2)))+'%)' as Failed, isnull(passed.Cantidad,0) as cantPassed, isnull(fail.Cantidad,0) as cantFailed, isnull(other.Cantidad,0) as cantOtrosEstados, tot.Cantidad as cantTotal from td.RUN rn left Join td.CYCLE (nolock)D on rn.RN_CYCLE_ID=D.CY_CYCLE_ID left join td.TESTCYCL (nolock) inst on inst.TC_TESTCYCL_ID=rn.RN_TESTCYCL_ID left join td.TEST (nolock) t on t.TS_TEST_ID=inst.TC_TEST_ID left join (select D.CY_CYCLE_ID as Suite, COUNT(1) as Cantidad	from td.RUN rn	Left Join td.CYCLE (nolock)D on rn.RN_CYCLE_ID=D.CY_CYCLE_ID where  RN_USER_01 like '%Autom%' and rn.RN_USER_02 is not null and RN_STATUS='Passed' and rn.RN_USER_03 ='"+robot+"' group by D.CY_CYCLE_ID) passed on passed.Suite=d.CY_CYCLE_ID left join (select D.CY_CYCLE_ID as Suite, COUNT(1) as Cantidad	from td.RUN rn	Left Join td.CYCLE (nolock)D on rn.RN_CYCLE_ID=D.CY_CYCLE_ID where  RN_USER_01 like '%Autom%' and rn.RN_USER_02 is not null and RN_STATUS='Failed' and rn.RN_USER_03 ='"+robot+"' group by D.CY_CYCLE_ID) fail on fail.Suite=d.CY_CYCLE_ID left join (select D.CY_CYCLE_ID as Suite, COUNT(1) as Cantidad	from td.RUN rn	Left Join td.CYCLE (nolock)D on rn.RN_CYCLE_ID=D.CY_CYCLE_ID where  RN_USER_01 like '%Autom%' and rn.RN_USER_02 is not null and RN_STATUS not in ('Failed','Passed') and rn.RN_USER_03 ='"+robot+"' group by D.CY_CYCLE_ID) other on other.Suite=d.CY_CYCLE_ID left join (select D.CY_CYCLE_ID as Suite, COUNT(1) as Cantidad	from td.RUN rn	Left Join td.CYCLE (nolock)D on rn.RN_CYCLE_ID=D.CY_CYCLE_ID where  RN_USER_01 like '%Autom%' and rn.RN_USER_02 is not null and rn.RN_USER_03 ='"+robot+"' group by D.CY_CYCLE_ID) tot on tot.Suite=d.CY_CYCLE_ID where RN_USER_01 like '%Autom%' and rn.RN_USER_02 is not null and rn.RN_USER_03 ='"+robot+"' group by D.CY_CYCLE,t.TS_USER_03,t.TS_USER_01,rn.RN_USER_02,passed.Cantidad,fail.Cantidad,other.Cantidad,tot.Cantidad order by t.TS_USER_03 desc";	
		}
	}
	
	
			
	
}
