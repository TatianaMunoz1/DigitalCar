import { React, useState, useEffect } from "react";
import "react-datepicker/dist/react-datepicker.css";
import "./Calendar.css";
import DatePicker, { registerLocale } from "react-datepicker";
import es from "date-fns/locale/es";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCalendar } from "@fortawesome/free-solid-svg-icons";
import Button from "../Button/Button";

registerLocale("es", es);

function Calendar(props) {
  const [startDate, setStartDate] = useState(new Date());
  const [endDate, setEndDate] = useState(new Date());

  return (
    <>
      <div className="dataPicker_container" data-testid="calendarTestId">
        <div
          className={
            props.months === 2 ? "datePicker_two_month" : "datePicker_one_month"
          }
        >
          <div className="datePicker">
            <FontAwesomeIcon icon={faCalendar} className="iconCalendar" />
            <DatePicker
              dateFormat="dd/MM/yyyy"
              minDate={new Date()}
              selected={(startDate, startDate)}
              selectsRange
              startDate={startDate}
              endDate={endDate}
              locale="es"
              monthsShown={props.months === 2 ? 2 : 1}
              onChange={(date) => {
                const [start, end] = date;
                setStartDate(start);
                setEndDate(end);
                if (props.setDates)
                  props.setDates({
                    startDate: start,
                    endDate: end,
                  });
              }}
            />
          </div>
        </div>
      </div>
    </>
  );
}

export default Calendar;
