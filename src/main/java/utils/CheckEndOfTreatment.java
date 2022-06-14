package utils;

import datastorage.ConnectionBuilder;
import datastorage.DAOFactory;
import datastorage.PatientDAO;
import datastorage.TreatmentDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class CheckEndOfTreatment {
    final ScheduledExecutorService scheduler;
    Connection conn;

    /**
     * creates a job that runs every day and deletes locked data older than 30 years
     */
    public CheckEndOfTreatment(){
        this.conn = ConnectionBuilder.getConnection();
        this.scheduler = Executors.newScheduledThreadPool(1);
    }

    public void executeJob(){
        final ScheduledFuture<?> taskHandle = scheduler.scheduleAtFixedRate(
                new Runnable() {
                    public void run() {
                        try {
                            deleteExpiredPatientData();
                        }catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 0, 5, TimeUnit.SECONDS);
    }

    /**
     * reads locked datasets and deletes them if they date back [more than] 30 years
     * @throws SQLException
     */
    private void deleteExpiredPatientData() throws SQLException {
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery("SELECT pid, treatmentend FROM patient WHERE delflag is not null");
        if(!result.next()){
            System.out.println("There currently is no locked data.");
        } else {
            do {
                if(checkIfOlderThan30years(DateConverter.convertStringToLocalDate(result.getString("treatmentend")))){
                    PatientDAO patientDAO = DAOFactory.getDAOFactory().createPatientDAO();
                    patientDAO.deleteById(result.getInt("pid"));
                    TreatmentDAO treatmentDAO = DAOFactory.getDAOFactory().createTreatmentDAO();
                    treatmentDAO.deleteByPid(result.getInt("pid"));
                    System.out.println("Patient " + result.getString("pid") + " has been deleted.");
                }
            } while (result.next());
        }
    }

    /**
     * returns true if the input dates back [more than] 30 years
     * @param date
     * @return boolean
     */
    private boolean checkIfOlderThan30years(LocalDate date){
        LocalDate today = LocalDate.now();
        Period intervalPeriod = Period.between(date, today);
        return (Math.abs(intervalPeriod.getYears())>=30);
    }



}
