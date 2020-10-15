/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage.pacientes;

import clases.restaFechas;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import sessions.HistorialConsultasFacadeLocal;
import sessions.ReservacionesConsultasFacadeLocal;
import wrapper.HistorialConsultasModel;
import wrapper.calendarModel;

@Named
@ViewScoped
public class scheduleJava8View implements Serializable {

    private ScheduleModel eventModel;
     //private ScheduleModel eventModelLibre;

    private ScheduleModel lazyEventModel;

    private ScheduleEvent event = new DefaultScheduleEvent();
    //private ScheduleEvent event1 = new DefaultScheduleEvent();

    private boolean showWeekends = true;
    private boolean tooltip = true;
    private boolean allDaySlot = true;

    private String timeFormat;
    private String slotDuration = "00:30:00";
    private String slotLabelInterval;
    private String scrollTime = "06:00:00";
    private String minTime = "04:00:00";
    private String maxTime = "20:00:00";
    private String locale = "en";
    private String timeZone = "";
    private String clientTimeZone = "local";
    private String columnHeaderFormat = "";
    private List<calendarModel> ListCalendar;
    private calendarModel selectedCalendar;
    private int idUsuario;
    @EJB
    private ReservacionesConsultasFacadeLocal reserva;
    @EJB
    private HistorialConsultasFacadeLocal historial;

    @PostConstruct
    public void init() {
        ListCalendar = new ArrayList<>();
        eventModel = new DefaultScheduleModel();
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        String idUsuarioStr = session.getAttribute("idUsuario").toString();
        idUsuario = Integer.parseInt(idUsuarioStr);
        restaFechas rest = new restaFechas();
        List<Object[]> buscaReserConsul = reserva.buscaReserConsul(idUsuario);
        int num=1;
        for (Object[] val : buscaReserConsul) {
            int id = (int)val[0];
            Date fecCita = (Date) val[1];
            int horIni = (int) val[2];
            int horFin = (int) val[3];
            Date fecActual = (Date) val[4];
            int cantD = rest.restaFechas(fecCita, fecActual);
            int estado =(int)val[8];
            ListCalendar.add(new calendarModel((int) val[0], (Date) val[1], (int) val[2], (int) val[3], (Date) val[4], (int) val[5], (int) val[6], (int) val[7], (int) val[8]));
            
            if (estado == 1 && cantD < 0){
                event = DefaultScheduleEvent.builder()
                        .title("Cita Finalizada")
                        .startDate(fecHourIni(cantD, horIni))
                        .endDate(fecHourFin(cantD, horFin))
                        .description("true")
                        .overlapAllowed(true).editable(true).styleClass("btn-otro")
                        .id(String.valueOf(id))
                        .dynamicProperty("1", id)
                        .build();
                eventModel.addEvent(event);
            }
            else if (estado == 1 && cantD == 0){
                event = DefaultScheduleEvent.builder()
                        .title("Cita Actual")
                        .startDate(fecHourIni(cantD, horIni))
                        .endDate(fecHourFin(cantD, horFin))
                        .description("true")
                        .overlapAllowed(true).editable(true).styleClass("btn-actual")
                        .id(String.valueOf(id))
                        .dynamicProperty("1", id)
                        .build();
                eventModel.addEvent(event);
            }
            else if(estado == 1 && cantD >= 1){
            event = DefaultScheduleEvent.builder()
                    .title("Cita Programada ")
                    .startDate(fecHourIni(cantD, horIni))
                    .endDate(fecHourFin(cantD, horFin))
                    .description("true")
                    .overlapAllowed(true).editable(true).styleClass("btn-futura")
                    .id(String.valueOf(id))
                    .dynamicProperty("1", id)
                    .build();
            eventModel.addEvent(event);
            }
            
        num++;
        }

        List<Object[]> buscaReserConsulLibres = reserva.buscaReserConsulLibres();
        for (Object[] val : buscaReserConsulLibres) {
            int id = (int)val[0];
            Date fecCita = (Date) val[1];
            int horIni = (int) val[2];
            int horFin = (int) val[3];
            Date fecActual = (Date) val[4];
            int cantD = rest.restaFechas(fecCita, fecActual);
            event = DefaultScheduleEvent.builder()
                    .title("Libre-"+id)
                    .startDate(fecHourIni(cantD, horIni))
                    .endDate(fecHourFin(cantD, horFin))
                    .description("false")
                    .overlapAllowed(true).editable(true)
                    .id(String.valueOf(id))
                    .build();
            eventModel.addEvent(event);
        }

       
        lazyEventModel = new LazyScheduleModel() {

            @Override
            public void loadEvents(LocalDateTime start, LocalDateTime end) {
                /* for (int i=1; i<=5; i++) {
                    LocalDateTime random = getRandomDateTime(start);
                    addEvent(DefaultScheduleEvent.builder().title("Lazy Event " + i).startDate(random).endDate(random.plusHours(3)).build());
                }*/
            }
        };
    }
    
    public void onGuardaCita(String evento){
        int idDoctor=1;
        int idEvento=0;
        int position = evento.trim().indexOf("-");
        String strId = evento.substring(position+1);
        idEvento =Integer.parseInt(strId); 
        boolean updateCita = reserva.updateCita(idUsuario, idDoctor, idEvento);
        boolean insertConsulat = historial.insertConsulat(idUsuario, idDoctor, idEvento);
        PrimeFaces.current().ajax().addCallbackParam("Actualizado", updateCita);
        PrimeFaces.current().ajax().addCallbackParam("view", "citaPaciente2.xhtml");
        init();
        
    }

    private LocalDateTime fecHourIni(int cant, int hour) {
        return LocalDateTime.now().plusDays(cant).withHour(hour).withMinute(0).withSecond(0).withNano(0);
    }

    private LocalDateTime fecHourFin(int cant, int hour) {
        return LocalDateTime.now().plusDays(cant).withHour(hour).withMinute(0).withSecond(0).withNano(0);
    }

   
    

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

   

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public void addEvent() {
        if (event.isAllDay()) {
            //see https://github.com/primefaces/primefaces/issues/1164
            if (event.getStartDate().toLocalDate().equals(event.getEndDate().toLocalDate())) {
                event.setEndDate(event.getEndDate().plusDays(1));
            }
        }

        if (event.getId() == null) {
            eventModel.addEvent(event);
        } else {
            eventModel.updateEvent(event);
        }

        event = new DefaultScheduleEvent();
    }

    public void onEventSelect(SelectEvent<ScheduleEvent> selectEvent) {
        System.out.println("selecciono "+selectEvent.getObject().getTitle());
        event = selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent<LocalDateTime> selectEvent) {
        event = DefaultScheduleEvent.builder().startDate(selectEvent.getObject()).endDate(selectEvent.getObject().plusHours(1)).build();
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Delta:" + event.getDeltaAsDuration());

        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Start-Delta:" + event.getDeltaStartAsDuration() + ", End-Delta: " + event.getDeltaEndAsDuration());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public boolean isShowWeekends() {
        return showWeekends;
    }

    public void setShowWeekends(boolean showWeekends) {
        this.showWeekends = showWeekends;
    }

    public boolean isTooltip() {
        return tooltip;
    }

    public void setTooltip(boolean tooltip) {
        this.tooltip = tooltip;
    }

    public boolean isAllDaySlot() {
        return allDaySlot;
    }

    public void setAllDaySlot(boolean allDaySlot) {
        this.allDaySlot = allDaySlot;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    public String getSlotDuration() {
        return slotDuration;
    }

    public void setSlotDuration(String slotDuration) {
        this.slotDuration = slotDuration;
    }

    public String getSlotLabelInterval() {
        return slotLabelInterval;
    }

    public void setSlotLabelInterval(String slotLabelInterval) {
        this.slotLabelInterval = slotLabelInterval;
    }

    public String getScrollTime() {
        return scrollTime;
    }

    public void setScrollTime(String scrollTime) {
        this.scrollTime = scrollTime;
    }

    public String getMinTime() {
        return minTime;
    }

    public void setMinTime(String minTime) {
        this.minTime = minTime;
    }

    public String getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(String maxTime) {
        this.maxTime = maxTime;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getClientTimeZone() {
        return clientTimeZone;
    }

    public void setClientTimeZone(String clientTimeZone) {
        this.clientTimeZone = clientTimeZone;
    }

    public String getColumnHeaderFormat() {
        return columnHeaderFormat;
    }

    public void setColumnHeaderFormat(String columnHeaderFormat) {
        this.columnHeaderFormat = columnHeaderFormat;
    }

    public List<calendarModel> getListCalendar() {
        return ListCalendar;
    }

    public void setListCalendar(List<calendarModel> ListCalendar) {
        this.ListCalendar = ListCalendar;
    }

    public calendarModel getSelectedCalendar() {
        return selectedCalendar;
    }

    public void setSelectedCalendar(calendarModel selectedCalendar) {
        this.selectedCalendar = selectedCalendar;
    }

   
}
