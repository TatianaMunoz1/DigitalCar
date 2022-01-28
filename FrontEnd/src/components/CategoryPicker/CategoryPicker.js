import React, { useState, useEffect } from "react";
import styles from "./CategoryPicker.module.css";
import axios from "axios";
import Url from "../../Url"

function CategoryPicker(props) {
  const [categories, setCategories] = useState([]);
  const [categoryId, setCategoryId] = useState(null);

  useEffect(() => {
    axios
      .get(Url+"/categories")
      .then((response) => {
        setCategories(response.data);
      })
      .catch((error) => {});
  }, []);

  useEffect(() => {
    props.handleCategoryPicker(categoryId);
  }, [categoryId]);

  const categoryOptions = categories.map((category) => (
    <option
      key={`${category.id}_${category.title}`}
      id={category.id.parseInt}
      value={category.id}
    >
      {category.title}
    </option>
  ));

  return (
    <div className={styles.category}>
      <select name="categories" onChange={(e) => setCategoryId(e.target.value)}>
        <option
          selected
          value=""
          disabled
          hidden
          className={styles.placeholder}
        >
          Seleccione una categoría
        </option>
        {categoryOptions}
      </select>
    </div>
  );
}

export default CategoryPicker;
