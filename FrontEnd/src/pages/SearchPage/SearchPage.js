import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import axios from "axios";
import SectionProducts from "../../components/SectionProducts/SectionProducts";
import Subheader from "../../components/Subheader/Subheader";
import styles from "./SearchPage.module.css";
import SearchPageBar from "../../components/SearchPageBar/SearchPageBar";
import Url from "../../Url";

function SearchPage(props) {
  const { search } = useLocation();
  const queryParams = new URLSearchParams(search);
  const [cars, setCars] = useState([]);
  const [carsXPage, setCarsXPage] = useState(4);

  const getCarsByFilter = () => {
    let url = "";

    const city = queryParams.get("city");
    const startDate = queryParams.get("startDate");
    const endDate = queryParams.get("endDate");
    const category = queryParams.get("category");

    if (city || startDate || endDate || category) {
      if (city !== null) url = "city=" + city;
      if (startDate)
        url += `${
          url.length > 0 ? "&" : ""
        }startDate=${startDate}&endDate=${endDate}`;
      if (category) url += `${url.length > 0 ? "&" : ""}category=${category}`;

      axios
        .get(Url+"/cars/filter?" + url)
        .then((response) => {
          setCars(response.data);
        })
        .catch((error) => {});
    } else {
      axios
        .get(Url+"/cars")
        .then((response) => {
          setCars(response.data);
        })
        .catch((error) => {});
    }
  };

  useEffect(() => {
    getCarsByFilter();
  }, [search]);

  // useEffect(() => {
  //   getCarsByFilter();
  // }, []);

  // useEffect(() => {
  //   const carsFiltered = location.state.carsFiltered;
  //   setCars(carsFiltered);
  // }, [location.state.carsFiltered]);

  return (
    <div className={styles.container}>
      <div className={styles.container_header}>
        <Subheader title={"Sección de busqueda"} link={"/"} />
      </div>

      <div className={styles.container_filters_results}>
        <div className={styles.filters}>
          <SearchPageBar />
        </div>
        <div className={styles.section_products}>
          <div className={styles.select_box}>
            <p>Cant: </p>
            <div className={styles.select_cant_elements}>
              <select
                name="countProducts"
                onChange={(e) => setCarsXPage(e.target.value)}
              >
                <option value="4">4</option>
                <option value="8">8</option>
                <option value="16">16</option>
                <option value="0">Todos</option>
              </select>
            </div>
          </div>
          <SectionProducts
            title={"Resultados"}
            cars={cars}
            carsXPage={carsXPage}
          />
        </div>
      </div>
    </div>
  );
}

export default SearchPage;
