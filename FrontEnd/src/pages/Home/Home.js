import React, { useEffect, useState } from "react";
import SearchBar from "../../components/SearchBar/SearchBar";
import SectionCategory from "../../components/SectionCartegory/SectionCategory";
import SectionProducts from "../../components/SectionProducts/SectionProducts";
import styles from "./Home.module.css";
import axios from "axios";
import Url from "../../Url";

const Home = (props) => {
  const [cars, setCars] = useState([]);

  const handlerSetCars = (list) => {
    setCars(list);
  };

  useEffect(() => {
    axios
      .get(Url+"/cars")
      .then((response) => {
        setCars(response.data);
      })
      .catch((error) => {});
  }, []);

  useEffect(() => {
    props.renderHeader();
    return () => props.renderHeader();
  }, []);

  return (
    <div className={styles.container}>
      <SearchBar handlerSetCars={handlerSetCars} />
      <div className={styles.container_box}>
        <SectionCategory handlerSetCars={handlerSetCars} />
        <SectionProducts title={"Recomendaciones"} cars={cars} />
      </div>
    </div>
  );
};

export default Home;
