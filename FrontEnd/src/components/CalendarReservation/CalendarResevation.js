import { React, useEffect, useState } from "react";
import "react-datepicker/dist/react-datepicker.css";
import "./CalendarReservation.css";
import DatePicker, { registerLocale } from "react-datepicker";
import es from "date-fns/locale/es";
import Swal from "sweetalert2";

registerLocale("es", es);

function CalendarReservation(props) {
  const typeOfCalendar = props.type;
  const [startDate, setStartDate] = useState(null);
  const [endDate, setEndDate] = useState(null);
  const datesExcluded = props.datesExcluded;

  const getDates = () => {
    if (datesExcluded)
      return datesExcluded.map((dateString) => {
        let date = new Date(dateString);
        date.setHours(date.getHours() + 3);
        return date;
      });
    else return [];
  };

  const handleSetDates = (start, end) => {
    if (datesExcluded && start !== null && end !== null) {
      datesExcluded.forEach((dateExcluded) => {
        if (
          dateExcluded > start.toISOString().slice(0, 10) &&
          dateExcluded < end.toISOString().slice(0, 10)
        ) {
          Swal.mixin({
            toast: true,
            position: "top-end",
            showConfirmButton: false,
            background: "#ffc2c2",
            timer: 3000,
            timerProgressBar: true,
            didOpen: (toast) => {
              toast.addEventListener("mouseenter", Swal.stopTimer);
              toast.addEventListener("mouseleave", Swal.resumeTimer);
            },
          }).fire({
            icon: "error",
            title:
              "<h4 style='color:#B00020'>El rango de fechas seleccionado contiene fechas no disponibles. </br> Por favor seleccione otro.</h4>",
          });

          setStartDate(null);
          setEndDate(null);
        }
      });
    }
  };

  useEffect(() => {
    typeOfCalendar === "calendar--picker" &&
      props.handleDateReservation(startDate, endDate);
  }, [startDate, endDate]);

  return (
    <>
      {typeOfCalendar === "calendar--picker" ? (
        <div className="datePicker_reservation" data-testid="calendarReservationTestId">
          <DatePicker
            typeOfCalendar={typeOfCalendar}
            dateFormat="dd/MM/yyyy"
            minDate={new Date()}
            locale="es"
            required
            inline
            excludeDates={getDates()}
            selected={(startDate, startDate)}
            selectsRange
            startDate={startDate}
            endDate={endDate}
            monthsShown={props.months === 2 ? 2 : 1}
            onChange={(date) => {
              const [start, end] = date;
              setStartDate(start);
              setEndDate(end);
              props.handleDateReservation(start, end);
              handleSetDates(start, end);
            }}
          />
        </div>
      ) : (
        <div className="datePicker_reservation only_view">
          <DatePicker
            dateFormat="dd/MM/yyyy"
            minDate={new Date()}
            locale="es"
            inline
            excludeDates={getDates()}
            monthsShown={props.months === 2 ? 2 : 1}
            selectsStart={false}
            selectsEnd={false}
            readOnly={true}
            startDate={startDate}
            endDate={endDate}
          />
        </div>
      )}
    </>
  );
}

export default CalendarReservation;
