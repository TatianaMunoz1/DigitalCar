import React, { useState, useEffect } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faMapMarkerAlt,
  faPlane,
  faBuilding,
  faChevronDown,
} from "@fortawesome/free-solid-svg-icons";
import styles from "./SearchCityBar.module.css";
import axios from "axios";
import Url from "../../Url";

function SearchCityBar(props) {
  const [filteredData, setFilteredData] = useState([]);
  const [selection, setSelection] = useState({
    id: "",
    name: "",
  });
  const [showAll, setShowAll] = useState(false);
  const [data, setData] = useState([]);

  useEffect(() => {
    axios
      .get(Url+"/cities")
      .then((response) => {
        setData(response.data);
      })
      .catch((error) => {});
  }, []);

  const handleFilter = (e) => {
    setShowAll(true);
    const searchWord = e.target.value;
    const newFilter = data.filter((value) => {
      return value.name.toLowerCase().includes(searchWord.toLowerCase());
    });
    searchWord === "" ? setFilteredData([]) : setFilteredData(newFilter);
  };

  useEffect(() => {
    props.handleFilterCity(selection.id, selection.name);
  }, [selection]);

  return (
    <div className={styles.search} data-testid="searchCityBarTestId">
      <div className={styles.searchInputs}>
        <FontAwesomeIcon icon={faMapMarkerAlt} className={styles.mapBtn} />
        <input
          type="text"
          placeholder="Ingrese ciudad o aeropuerto"
          autoComplete="off"
          onClick={() => {
            setShowAll(!showAll);
            setSelection({ id: "", name: "" });
          }}
          onChange={handleFilter}
          className={styles.input}
          id="input_search_city"
          value={selection.name !== "" ? selection.name : undefined}
        />
        <FontAwesomeIcon
          icon={faChevronDown}
          className={styles.iconChevron}
          onClick={() => setShowAll(!showAll)}
        />
      </div>
      {
        <div
          className={
            showAll
              ? `${styles.dataResult}`
              : `${styles.dataResult} ${styles.hidden}`
          }
        >
          {(showAll && filteredData.length === 0
            ? data
            : filteredData.slice(0, 15)
          ).map((city) => {
            return (
              <div
                className={styles.options}
                key={`${city.id}_${city.country}`}
                onClick={() => {
                  setSelection({ id: city.id, name: city.name });
                  setShowAll(false);
                  setFilteredData([]);
                  props.handleFilterCity(city.id, city.name);
                }}
              >
                <FontAwesomeIcon
                  icon={city.type === "ciudad" ? faBuilding : faPlane}
                  className={styles.mapBtnOption}
                />
                <p className={styles.firstLine} id={city.id} title={city.name}>
                  {city.name}
                </p>
                <p className={styles.secondLine} id={city.id} title={city.name}>
                  {city.country}
                </p>
              </div>
            );
          })}
        </div>
      }
    </div>
  );
}

export default SearchCityBar;
