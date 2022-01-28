import React, { useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCheckCircle } from "@fortawesome/free-regular-svg-icons";
import ReactDatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import "./TimePicker.css";

function TimePicker(props) {
  const [startTime, setStartTime] = useState(null);

  return (
    <div className="container_timePicker">
      <p className="container_timePicker_title">
        <FontAwesomeIcon
          icon={faCheckCircle}
          className="checkIcon_timePicker"
        />
        &nbsp; Tu vehículo va a estar listo para retirar entre las 00:00 AM y
        las 11:00 PM
      </p>
      <div className="timePicker" data-testid="TimePickerTestId">
        <p className="container_timePicker_subtitle">
          Indicá tu horario estimado de retiro
        </p>
        <ReactDatePicker
          selected={startTime}
          onChange={(date) => setStartTime(date)}
          onSelect={props.handleTimeReservation(startTime)}
          placeholderText="Seleccionar hora"
          required={true}
          showTimeSelect
          showTimeSelectOnly
          timeIntervals={60}
          timeCaption="Horarios"
          dateFormat="h:mm aa"
          locale="es"
        />
      </div>
    </div>
  );
}

export default TimePicker;
