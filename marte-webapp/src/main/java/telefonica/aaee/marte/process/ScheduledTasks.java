package telefonica.aaee.marte.process;

import java.text.SimpleDateFormat;
import java.util.Date;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

    final static Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	
	@Autowired
	private MailUtil mailUtil;

	@Scheduled(cron = "0 0/3 * * * *") //Cada 3 minutos (sec min hour day day_of_month month day_of_week year)
    public void every3min() {
		logger.info("Cada 3 minutos. Hora actual : [{}]", dateFormat.format(new Date()));
    }

	@Scheduled(cron = "0 13 0/1 * * *") //Cada 1 hora (sec min hour day day_of_month month day_of_week year)
    public void everyHour() {
		logger.info("Cada hora. Hora actual : [{}]", dateFormat.format(new Date()));
    }
}
