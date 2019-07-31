package com.adhd.Shits;

import java.io.IOException;
import java.io.PrintWriter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ReadDump {

    public static void main(String[] args) {
        String[] cids = new String[] {
            "193514567048919",
            "123145760774999",
            "123145932835834",
            "123145783826629",
            "123146112846549",
            "123145754648127",
            "123145828087874",
            "193514716557064",
            "1291458470",
            "123145992897089",
            "123146021814174",
            "123145825370289",
            "123145815376389",
            "1435366925",
            "193514477008409",
            "1381276715",
            "123145728998122",
            "123146033768644",
            "193514735862679",
            "706134650",
            "123145710386557",
            "193514781841604",
            "1363873565",
            "1286730045",
            "1238931850",
            "106340685",
            "123145721349814",
            "193514304368332",
            "97400942",
            "123145770050744",
            "123145825389329",
            "833209385",
            "1445343375",
            "706139175",
        };
        PrintWriter writer = null;
        String header = null;
        try {
            writer = new PrintWriter("Output.csv");
            header = "CompanyId, Cancel Date, Company Type, Platform";
            writer.append(header);
            writer.append(System.lineSeparator());

            for (String realmId : cids) {
                String row = realmId + " ,";
                try {
                    row += read(realmId);
                } catch (Exception e) {
                    System.out.println("Error reading for realmId " + realmId);
                }
                
                writer.append(row);
                writer.append(System.lineSeparator());
            }

            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
            try {
                writer.flush();
                writer.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            
        }

        System.out.println("Ended.....");
    }

    public static String read(String realmId) {
        System.out.println("Fetching data for "+ realmId);
        String html = "https://admin-c72.qbo.intuit.com/qbo72/company_dump_backend?action=Dump%252520It&authid="+realmId;
      try {
        

        Connection conn = Jsoup.connect(html).timeout(30 * 1000);
        conn.header("Accept", "text/html");
        conn.header("Cookie", "UserLocale=en-US; qboeuid=ab4c20d1.58e0208a996ac; s_fid=1B7202E15227E9CB-09826ED2041C554D; s_vi=[CS]v1|2E99F1668503514D-60001194A0000086[CE]; ivid_b=36737d43-28a5-4eab-8a65-8c0c48840779; ivid=e3d19da5-07cf-4869-8976-5600de426361; did=SHOPPER2_7000b62df03cc44b3dbdb6cde406ec668182af19c388bfd597e107e7376f13c0d37df6fc68a8a0d3cc5640c5166f9aef; _ga=GA1.2.177233249.1563772860; ius_session=273D32DEAB664810A5BC3C2A04280CC8; s_sq=intuitqboharmony%3D%2526pid%253Dqbo%25257Cdp%25252F%2526pidt%253D1%2526oid%253Dfunctiontc%252528%252529%25257B%25257D%2526oidt%253D2%2526ot%253DSUBMIT; ADRUM=s=1563933113883&r=https%3A%2F%2Fjira.intuit.com%2Fbrowse%2FBTBS-9179%3F0; OAM_GITO=sQN7W9ZEMU0kmapsw6v1uw==~8vw1+xPgK+1+vPqzlfz1zlFvbHnR7hYdnq2XukZo3QQDde8QIC3SY0dy7t6DNhl9M//18XJHV5tsn36mI5UDo/Y9lSK3ULuOG788fDT81hyoTQKtl9REwqblGpU9OBZ7biRonT89y4IiZ9ApNUS2KOVl2fHtKo5/CvyMF1kmEyoNF9Fbx3qzuMGQQpXAWtfcFdrvImwF7ZPwWcbtCmh5GtkBlCKzwLkH/OQm5bb1QO4=; JSESSIONID=6E3945FADB61D324C060C34685F31E49.c1-pprdc1uw2ada023209-stack-a; qbn.ptc.url=https%3A%2F%2Fadmin-c37.e2e.qbo.intuit.com%2Fqbo37%2Fsql; qbn.url=https%3A%2F%2Fadmin-c72.qbo.intuit.com%2Fqbo72%2Fcompany_dump_backend%3Faction%3DDump%2BIt%26authid%3D1113719515; qbn.tkt=V1-2-X0xgn0o2zr55s0mbb5tj5d; qbn.parentid=50000000; qbn.agentid=123145724617979; AMCVS_969430F0543F253D0A4C98C6%40AdobeOrg=1; userIdentifier=9130347082475196; qbo.agentid=193514509651249; qbo.parentid=123145886905094; fms_mktg_pg_vw=y; LPSID-19175958=M58EShERTTmJCxo64eE0cA; qbo.cluster=15; qbo.ptc.parentid=9130347082483446; qbo.ptc.qbo_version=1916.056; qbo.ptc.partnerInfo=\"partner=NONE|redirectrequired=false\"; qbo.ptc.login_test=login_test; rxVisitor=15627346753214SVB97QVP5U856EI0LVAHGUVQIG147AE; dtPC=3$334675313_747h-vGFLFDJNPLPCCDBPMCGFCCONKMFDMFHLJ; rxvt=1562737421460|1562734675324; dtSa=-; dtLatC=268; LPSID-44884138=4ZaKXFR5R2edy9XDLXl5lQ; iop.target_url=\"\"; StartQBOMap=1562735437687; iop_appid=6e62d739.s_1563358156740; _environment=pre-prod; iop.onlinepayroll.bis~IOP~qbo-e2e1-lasf01.onlinepayroll.intuit.com-vs_tcp443-pool_tcp2080=rd737o00000000000000000000ffff0aa10877o2080; dtCookie==3=srv=3=sn=70E74B9ADB1070BB01664A89F12A35DB=perc=100000=ol=0=mul=1=app:48f25aece242968f=0; qbo.ptc.signin_tid=loginserver_7af94f72-f254-445f-b1a6-9fcf23a45b2c; iop.onlinepayroll.AWSALB=Q33R7oGsL5JOQaleSW7Yz4iWcTAGmMbUYdTrcqQwN4HPnnfJnCy503s9wQUIT3v56OalUMqhPNfTq8eodXa+XYcjZ+u8U6oCQD7/Jo0Jbw0oVuL6d+W+EuCI3QJo; IOPJSESSIONID=E8198B4139EFB5E1C58EFD20368F6460.TST01; qbn.ptc.login_test=login_test; qbn.ptc.signin_tid=loginserver_58573f89-851b-4e69-b4ad-9dff977c1ce2; lpasinc_tid=login_123146492510354_d57a7f29-a7ed-4ffe-b274-3edce2b28195; s_cc=true; ivid_synced=true; qbn.ptc.parentid=50000000; qbn.ptc.tkt=V1-89-X3p9ov7q5vglvs8ptv3yvb; qbn.ptc.agentid=123146492510354; OAMAuthnHintCookie=1; qbn.ptc.gauthid=123146492510354; qbn.ptc.ticket=V1-89-X3p9ov7q5vglvs8ptv3yvb; qbn.ptc.authid=123146492510354; qbn.gauthid=123145724617979; qbn.ticket=V1-2-X0xgn0o2zr55s0mbb5tj5d; qbn.authid=123145724617979; qbo.clientType=web");
        Document doc = conn.get();
        

        Elements tableElements = doc.select("p");
        StringBuilder row = new StringBuilder();

        boolean deletedFound = false;;
        String companyType = "";
        String billingCode = "";
        //tableElements.forEach((element) -> {
            
        for (Element element :  tableElements) {
            boolean hasDeletedText = element.html().contains("COMPANY IS SCHEDULED TO BE DELETED");
            if (hasDeletedText) {
                deletedFound = true;
            }
            

            boolean isCompanyInfoNode = element.html().contains("Company Info");
            if (isCompanyInfoNode) {
                int startIndex = element.html().indexOf("Company Type(QBO/DATA_ONLY/OTHERs)");
                int startIndexOfType2 = element.html().indexOf("Company Type(Harmony/Classic)");
                
                String Type = element.html().substring(startIndex, startIndexOfType2-5);
                //System.out.println("Type =>" + Type);
                if (Type.contains(":")) {
                    int ind = Type.indexOf(":");
                    companyType = Type.substring(ind+1);
                } else {
                    companyType = Type;
                }
            }

            boolean isBillingCode = element.html().contains("Billing Code: ");
            if (isBillingCode) {
                int startIndex = element.html().indexOf("Billing Code: ");
                int endIndex = element.html().indexOf("User Count");
                String BCode = element.html().substring(startIndex, endIndex-4);
                //System.out.println(BCode);
                //row.append(BCode);
                //billingCode = BCode;

                if (BCode.contains("OBI-LL4")) {
                    billingCode = "OBILL";
                } else {
                    billingCode = "QBN";
                }
            }
        }

        //row.append(realmId + " ,");
        row.append(deletedFound ? "Found, " : "Not Found, ");
        row.append(companyType + " ,");
        row.append(billingCode );

        return row.toString();

      } catch (IOException e) {
         e.printStackTrace();
      }

      return "";
    }
}