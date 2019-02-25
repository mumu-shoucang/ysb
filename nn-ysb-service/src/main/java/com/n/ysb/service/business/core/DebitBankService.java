package com.n.ysb.service.business.core;

import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class DebitBankService {

	private static final Logger log = LoggerFactory.getLogger(DebitBankService.class);
	
	@Autowired
	private ApplicationContext appContext;
	@Autowired
	private BankAliService bankAliService;
	
	interface BankMatcher {
		boolean match(String bankNo);
	}
	
	class Bank {
		private String code;
		private String name;
		private BankMatcher bankMatcher;
		
		public Bank(String code, String name, BankMatcher bankMatcher) {
			this.code = code;
			this.name = name;
			this.bankMatcher = bankMatcher;
		}

		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	}
	
	@Bean
	private Bank ICBC() {
		return new Bank("ICBC", "工商银行", new BankMatcher() {
			
			@Override
			public boolean match(String bankNo) {
				String regex = "^(620200|620302|620402|620403|620404|620406|620407|620409|620410|620411|620412|620502|620503|620405|620408|620512|"
						+ "620602|620604|620607|620611|620612|620704|620706|620707|620708|620709|620710|620609|620712|620713|620714|620802|620711|"
						+ "620904|620905|621001|620902|621103|621105|621106|621107|621102|621203|621204|621205|621206|621207|621208|621209|621210|"
						+ "621302|621303|621202|621305|621306|621307|621309|621311|621313|621211|621315|621304|621402|621404|621405|621406|621407|"
						+ "621408|621409|621410|621502|621317|621511|621602|621603|621604|621605|621608|621609|621610|621611|621612|621613|621614|"
						+ "621615|621616|621617|621607|621606|621804|621807|621813|621814|621817|621901|621904|621905|621906|621907|621908|621909|"
						+ "621910|621911|621912|621913|621915|622002|621903|622004|622005|622006|622007|622008|622010|622011|622012|621914|622015|"
						+ "622016|622003|622018|622019|622020|622102|622103|622104|622105|622013|622111|622114|622017|622110|622303|622304|622305|"
						+ "622306|622307|622308|622309|622314|622315|622317|622302|622402|622403|622404|622313|622504|622505|622509|622513|622517|"
						+ "622502|622604|622605|622606|622510|622703|622715|622806|622902|622903|622706|623002|623006|623008|623011|623012|622904|"
						+ "623015|623100|623202|623301|623400|623500|623602|623803|623901|623014|624100|624200|624301|624402|623700|624000|"
						+ "622200|622202|622203|622208|621225|620058|621281|900000|621558|621559|621722|621723|620086|621226|621618|620516|621227|"
						+ "621288|621721|900010|623062|621670|621720|621379|621240|621724|621762|621414|621375|622926|622927|622928|622929|622930|"
						+ "622931|621733|621732|621372|621369|621763|"
						+ "402791|427028|427038|548259|621376|621423|621428|621434|621761|621749|621300|621378|622944|622949|621371|621730|621734|"
						+ "621433|621370|621764|621464|621765|621750|621377|621367|621374|621731|621781|"
						+ "9558)\\d{10,}";
				return Pattern.matches(regex, bankNo);
			}
		});
	}
	
	@Bean
	private Bank ABC() {
		return new Bank("ABC", "农业银行", new BankMatcher() {
			
			@Override
			public boolean match(String bankNo) {
				String regex = "^(622841|622824|622826|622848|620059|621282|622828|622823|621336|621619|622821|622822|622825|622827|622845|622849|"
						+ "623018|623206|621671|622840|622843|622844|622846|622847|620501|"
						+ "95595|95596|95597|95598|95599|"
						+ "103)\\d{13,}";
				return Pattern.matches(regex, bankNo);
			}
		});
	}
	
	@Bean
	private Bank BOC() {
		return new Bank("BOC", "中国银行", new BankMatcher() {
			
			@Override
			public boolean match(String bankNo) {
				String regex = "^(621660|621661|621662|621663|621665|621667|621668|621669|621666|456351|601382|621256|621212|621283|620061|621725|"
						+ "621330|621331|621332|621333|621297|621568|621569|621672|623208|621620|621756|621757|621758|621759|621785|621786|621787|"
						+ "621788|621789|621790|622273|622274|622771|622772|622770|621741|621041|"
						+ "621293|621294|621342|621343|621364|621394|621648|621248|621215|621249|621231|621638|621334|621395|623040|622348)\\d{10,}";
				return Pattern.matches(regex, bankNo);
			}
		});
	}
	
	@Bean
	private Bank CCB() {
		return new Bank("CCB", "建设银行", new BankMatcher() {
			
			@Override
			public boolean match(String bankNo) {
				String regex = "^(621284|436742|589970|620060|621081|621467|621598|621621|621700|622280|622700|623211|623668|"
						+ "421349|434061|434062|524094|526410|552245|621080|621082|621466|621488|621499|622966|622988|622382|621487|621083|621084|"
						+ "620107|"
						+ "436742193|622280193)\\d{10,}";
				return Pattern.matches(regex, bankNo);
			}
		});
	}
	
	@Bean
	private Bank COMM() {
		return new Bank("COMM", "交通银行", new BankMatcher() {
			
			@Override
			public boolean match(String bankNo) {
				String regex = "^(622261|622260|622262|621002|621069|621436|621335|"
						+ "620013|"
						+ "405512|601428|405512|601428|622258|622259|405512|601428)\\d{10,}";
				return Pattern.matches(regex, bankNo);
			}
		});
	}
	
	@Bean
	private Bank CMB() {
		return new Bank("CMB", "招商银行", new BankMatcher() {
			
			@Override
			public boolean match(String bankNo) {
				String regex = "^(402658|410062|468203|512425|524011|622580|622588|622598|622609|95555|621286|621483|621485|621486|621299|"
						+ "690755)\\d{9,}";
				return Pattern.matches(regex, bankNo);
			}
		});
	}
	
	@Bean
	private Bank CMBC() {
		return new Bank("CMBC", "民生银行", new BankMatcher() {
			
			@Override
			public boolean match(String bankNo) {
				String regex = "^(622615|622616|622618|622622|622617|622619|415599|421393|421865|427570|427571|472067|472068|622620)\\d{10,}";
				return Pattern.matches(regex, bankNo);
			}
		});
	}
	
	@Bean
	private Bank CEB() {
		return new Bank("CEB", "光大银行", new BankMatcher() {
			
			@Override
			public boolean match(String bankNo) {
				String regex = "^(303|"
						+ "90030|"
						+ "620535|"
						+ "620085|622660|622662|622663|622664|622665|622666|622667|622669|622670|622671|622672|622668|622661|622674|622673|"
						+ "620518|621489|621492)\\d{10}";
				return Pattern.matches(regex, bankNo);
			}
		});
	}
	
	@Bean
	private Bank CITIC() {
		return new Bank("CITIC", "中信银行", new BankMatcher() {
			
			@Override
			public boolean match(String bankNo) {
				String regex = "^(433670|433680|442729|442730|620082|622690|622691|622692|622696|622698|622998|622999|433671|968807|968808|"
						+ "968809|621771|621767|621768|621770|621772|621773|622453|622456|"
						+ "622459)\\d{10}";
				return Pattern.matches(regex, bankNo);
			}
		});
	}
	
	@Bean
	private Bank HXBANK() {
		return new Bank("HXBANK", "华夏银行", new BankMatcher() {
			
			@Override
			public boolean match(String bankNo) {
				String regex = "^(622630|622631|622632|622633|999999|621222|623020|623021|623022|623023)\\d{10,}";
				return Pattern.matches(regex, bankNo);
			}
		});
	}
	
	@Bean
	private Bank CIB() {
		return new Bank("CIB", "兴业银行", new BankMatcher() {
			
			@Override
			public boolean match(String bankNo) {
				String regex = "^(438589|"
						+ "90592|"
						+ "966666|622909|438588|622908)\\d{10,}";
				return Pattern.matches(regex, bankNo);
			}
		});
	}
	
	@Bean
	private Bank SPDB() {
		return new Bank("SPDB", "上海浦东发展银行", new BankMatcher() {
			
			@Override
			public boolean match(String bankNo) {
				String regex = "^(622516|622517|622518|622521|622522|622523|984301|984303|621352|621793|621795|621796|621351|621390|621792|621791|"
						+ "84301|84336|84373|84385|84390|87000|87010|87030|87040|84380|84361|87050|84342)\\d{10,}";
				return Pattern.matches(regex, bankNo);
			}
		});
	}
	
	@Bean
	private Bank GDB() {
		return new Bank("GDB", "广发银行", new BankMatcher() {
			
			@Override
			public boolean match(String bankNo) {
				String regex = "^(622516|622517|622518|622521|622522|622523|984301|984303|621352|621793|621795|621796|621351|621390|621792|621791|"
						+ "622568|6858001|6858009|621462|"
						+ "9111)//d{10,}";
				return Pattern.matches(regex, bankNo);
			}
		});
	}
	
	@Bean
	private Bank BJBANK() {
		return new Bank("BJBANK", "北京银行", new BankMatcher() {
			
			@Override
			public boolean match(String bankNo) {
				String regex = "^(623111|421317|422161|602969|422160|621030|621420|621468)\\d{10,}";
				return Pattern.matches(regex, bankNo);
			}
		});
	}
	
	// 平安银行 (3)填写中文名称，平安银 行请写成:深圳发展银行。
	Bank SPABANK = new Bank("SPABANK", "深圳发展银行", new BankMatcher() {
		
		@Override
		public boolean match(String bankNo) {
			String regex = "^(621626|623058|"
					+ "602907|622986|622989|622298|627069|627068|627066|627067|412963|415752|415753|622535|622536|622538|622539|998800|412962|"
					+ "622983)\\d{10,}";
			return Pattern.matches(regex, bankNo);
		}
	});
	
	@Bean
	private Bank SDB() {
		return new Bank("SDB", "深圳发展银行", new BankMatcher() {
			
			@Override
			public boolean match(String bankNo) {
				String regex = "^(621626|623058|"
						+ "602907|622986|622989|622298|627069|627068|627066|627067|412963|415752|415753|622535|622536|622538|622539|998800|412962|"
						+ "622983)\\d{10,}";
				return Pattern.matches(regex, bankNo);
			}
		});
	}
	
	
	public String matchBank(String bankNo) {
		Map<String, Bank> vos = appContext.getBeansOfType(Bank.class);
		String bankName = "";
		for(Map.Entry<String, Bank> entry : vos.entrySet()) {
			if(entry.getValue().bankMatcher.match(bankNo)) {
				bankName = entry.getValue().getName();
			}
		}
		// get bankcode from ali
		if(StringUtils.isEmpty(bankName)) {
			String bankCode = this.getDCBankCodeFromAli(bankNo);
			if(vos.keySet().contains(bankCode)) {
				bankName = vos.get(bankCode).getName();
			}
		}
		
		return bankName;
	}
	
	private String getDCBankCodeFromAli(String bankNo) {
		String bankCode = bankAliService.getBankCodeFromAli(bankNo, "DC");
		log.info("get DC bankcode from ali: {} --{}", bankNo, bankCode);
		return bankCode;
	}
	
	public static void main(String[] args) {
		DebitBankService db = new DebitBankService();
		boolean bl = db.ABC().bankMatcher.match("6212260200007053");
		System.out.println(bl);
	}
	
}
