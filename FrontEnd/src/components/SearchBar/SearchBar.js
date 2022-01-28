import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import Calendar from "../Calendar/Calendar";
import SearchCityBar from "../SearchCityBar/SearchCityBar";
import styles from "./SearchBar.module.css";
import Button from "../Button/Button";

function SearchBar(props) {
  const history = useHistory();
  // const [carsFiltered, setCarsFiltered] = useState([]);
  const [succesSearch, setSuccesSearch] = useState(false);
  const [selectedCity, setSelectedCity] = useState(null);
  const [dates, setDates] = useState({
    startDate: "",
    endDate: "",
  });

  const handlerSetDates = (calendarDates) => {
    setDates(calendarDates);
  };

  const handleFilterCity = (id, name) => {
    setSelectedCity(name);
  };

  const handlerOnClick = (e) => {
    e.preventDefault();

    let url = "";

    if (selectedCity !== "") url = "city=" + selectedCity;
    if (
      dates.startDate !== "" &&
      dates.endDate !== "" &&
      dates.startDate !== null &&
      dates.endDate !== null
    )
      url += `${url.length > 0 ? "&" : ""}startDate=${dates.startDate
        .toISOString()
        .slice(0, 10)}&endDate=${dates.endDate.toISOString().slice(0, 10)}`;

    history.push(`/search-page?${url}`);
  };

  return (
    <div
      className={
        succesSearch ? styles.container_after_search : styles.container
      }
      data-testid="searchBarTestId"
    >
      <h1 className={styles.title}>
        Buscá las mejores ofertas en vehículos de alquiler
      </h1>
      <form className={styles.container_search}>
        <div className={styles.city_picker}>
          <SearchCityBar handleFilterCity={handleFilterCity} />
        </div>
        <div className={styles.day_picker}>
          <div className={styles.calendar_one_month}>
            <Calendar setDates={handlerSetDates} months={1} />
          </div>
          <div className={styles.calendar_two_months}>
            <Calendar setDates={handlerSetDates} months={2} />
          </div>
        </div>
        <div className={styles.btn_container}>
          <Button
            title="Buscar"
            link="/"
            type="button"
            btnStyle="btn--full"
            btnSize="btn--responsive"
            onClick={handlerOnClick}
          />
        </div>
      </form>

      <div
        className={succesSearch ? styles.image_after_search : styles.image}
      ></div>
    </div>
  );
}

export default SearchBar;
