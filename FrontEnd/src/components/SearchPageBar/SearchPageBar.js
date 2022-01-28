import React, { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";
import Calendar from "../Calendar/Calendar";
import SearchCityBar from "../SearchCityBar/SearchCityBar";
import styles from "./SearchPageBar.module.css";
import Button from "../Button/Button";
import axios from "axios";
import Url from "../../Url";

function SearchPageBar(props) {
  const history = useHistory();
  const [carsFiltered, setCarsFiltered] = useState([]);
  const [succesSearch, setSuccesSearch] = useState(false);
  const [selectedCity, setSelectedCity] = useState(null);
  const [categories, setCategories] = useState([]);
  const [categorySelected, setCategorySelected] = useState("");

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
    if (categorySelected !== "")
      url += `${url.length > 0 ? "&" : ""}category=${categorySelected}`;
    if (
      dates.startDate !== "" &&
      dates.endDate !== "" &&
      dates.startDate !== null &&
      dates.endDate !== null
    )
      url += `${url.length > 0 ? "&" : ""}startDate=${dates.startDate
        .toISOString()
        .slice(0, 10)}&endDate=${dates.endDate.toISOString().slice(0, 10)}`;

    history.replace("/search-page?" + url);
  };

  const handlerCategoryChange = (e) => {
    if (categorySelected !== e.target.value)
      setCategorySelected(e.target.value);
    else {
      setCategorySelected("");
      e.target.checked = false;
    }
  };

  useEffect(() => {
    axios
      .get(Url+"/categories")
      .then((response) => {
        setCategories(response.data);
      })
      .catch((error) => {});
    // document.querySelector(`#${props.cat}`).checked = true;
  }, []);

  return (
    <div className={styles.container} data-testid="searchBarTestId">
      <form className={styles.container_search}>
        <div className={styles.container_search_box}>
          <div className={styles.city_picker}>
            <label className={styles.label}>Lugar de entrega</label>
            <SearchCityBar handleFilterCity={handleFilterCity} />
          </div>
          <div className={styles.day_picker}>
            <label className={styles.label}>Fecha Retiro/Devolución</label>
            <div className={styles.calendar_one_month}>
              <Calendar setDates={handlerSetDates} months={1} />
            </div>
            <div className={styles.calendar_two_months}>
              <Calendar setDates={handlerSetDates} months={2} />
            </div>
          </div>
        </div>
        <div className={styles.category_box}>
          <label className={styles.label}>Categorías</label>
          <div className={styles.category_container}>
            {categories.map((category) => (
              <div
                className={styles.input_checkbox}
                key={`${category.id}_${category.title}`}
              >
                <label
                  className={styles.container_checkbox}
                  htmlFor={category.title}
                >
                  {category.title}
                  <input
                    onClick={handlerCategoryChange}
                    // onChange={handlerCategoryChange}
                    type="radio"
                    id={category.title}
                    name="category"
                    value={category.title}
                  />
                  <span className={styles.checkmark}></span>
                </label>
              </div>
            ))}
          </div>
        </div>

        <div className={styles.btn_container}>
          <Button
            title="Buscar"
            type="button"
            btnStyle="btn--full"
            btnSize="btn--responsive"
            onClick={handlerOnClick}
          />
        </div>
      </form>
    </div>
  );
}

export default SearchPageBar;
