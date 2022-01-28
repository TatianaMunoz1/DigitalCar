import React, { useState } from "react";
import { useHistory } from "react-router";
import styles from "./Administration.module.css";
import SearchCityBar from "../SearchCityBar/SearchCityBar";
import CategoryPicker from "../CategoryPicker/CategoryPicker";
import Button from "../Button/Button";
import AddFeatures from "../AddFeatures/AddFeatures";
import AddImages from "../AddImages/AddImages";
import Inputs from "../Inputs/Inputs";
import Url from "../../Url"
import axios from "axios";
import Swal from "sweetalert2";

function Administration(props) {
  const history = useHistory();
  const [newCarFeatures, setNewCarFeatures] = useState([]);
  const [newCarImages, setNewCarImages] = useState([]);
  const [submit, setSubmit] = useState(null);
  const [newCar, setNewCar] = useState({
    name: "",
    description: "",
    address: "",
    category: { id: "" },
    city: { id: "" },
  });

  const expressions = {
    input: /^[a-zA-Z0-9_.+-:", ]+$/,
  };

  const handleInputChange = (e) => {
    const target = e.target;
    const value = target.value;
    const name = target.name;

    setNewCar({
      ...newCar,
      [name]: value,
    });
  };

  const handleFilterCity = (cityId, title) => {
    setNewCar({ ...newCar, city: { id: cityId } });
  };
  const handleAddNewFeatures = (newCarFeatures) => {
    setNewCarFeatures([...newCarFeatures]);
  };
  const handleAddNewImages = (newCarImages) => {
    setNewCarImages([...newCarImages]);
  };
  const handleCategoryPicker = (categoryId) => {
    setNewCar({ ...newCar, category: { id: categoryId } });
  };

  const onSubmit = (e) => {
    e.preventDefault();
    setSubmit(true);

    if (
      newCar.name !== "" &&
      newCar.description !== "" &&
      newCar.category.id !== "" &&
      newCar.address !== "" &&
      newCar.city.id !== "" &&
      newCarFeatures.length > 0 &&
      newCarImages.length >= 4
    ) {
      const data = {
        name: `${newCar.name}`,
        description: `${newCar.description}`,
        address: newCar.address,
        features: newCarFeatures,
        category: {
          id: newCar.category.id,
        },
        images: [...newCarImages],
        city: {
          id: newCar.city.id,
        },
      };
      const config = {
        headers: { Authorization: props.user.token },
      };
      axios
        .post(Url+"/cars", data, config)
        .then(function (response) {
          if (response.status === 201) {
            Swal.fire({
              icon: "success",
              title: "Nuevo vehículo creado con exito.",
              text: "¿Desea agregar más vehículos?",
              showDenyButton: true,
              confirmButtonText: "Agregar más",
              denyButtonText: `No agregar más`,
            }).then((result) => {
              if (result.isConfirmed) {
              } else if (result.isDenied) {
                history.push("/succes-add-new-product");
              }
            });
          } else {
            const Toast = Swal.mixin({
              toast: true,
              position: "top-end",
              showConfirmButton: false,
              background: "#ffc2c2",
              timer: 5000,
              timerProgressBar: true,
              didOpen: (toast) => {
                toast.addEventListener("mouseenter", Swal.stopTimer);
                toast.addEventListener("mouseleave", Swal.resumeTimer);
              },
            });
            Toast.fire({
              icon: "error",
              title:
                "<h4 style='color:#B00020'>Lamentablemente el producto no ha podido crearse. </br>Por favor intente más tarde.</h4>",
            });
          }
        })
        .catch(function (error) {
          const Toast = Swal.mixin({
            toast: true,
            position: "top-end",
            showConfirmButton: false,
            background: "#ffc2c2",
            timer: 5000,
            timerProgressBar: true,
            didOpen: (toast) => {
              toast.addEventListener("mouseenter", Swal.stopTimer);
              toast.addEventListener("mouseleave", Swal.resumeTimer);
            },
          });
          Toast.fire({
            icon: "error",
            title:
              "<h4 style='color:#B00020'>Lamentablemente el producto no ha podido crearse. </br>Por favor intente más tarde.</h4>",
          });
        });
    }
  };

  return (
    <div className={styles.container}>
      <form className={styles.form} onSubmit={onSubmit}>
        <div>
          <h2 className={styles.title}>Datos del vehículo</h2>
          {(newCar.name === "" ||
            newCar.description === "" ||
            newCar.category.id === "" ||
            newCar.address === "" ||
            newCar.city.id === "") &&
            submit && (
              <p className={styles.error_message}>
                *Todos los campos son obligatorios. Por favor complete los
                campos vacios.
              </p>
            )}
        </div>
        <div className={styles.form_columns}>
          <div className={styles.form_first_section}>
            <div>
              <Inputs
                onChange={handleInputChange}
                value={newCar.name}
                name="name"
                type="text"
                label="Modelo del vehículo"
                errorLegend="Ingrese el módelo del vehículo."
                regularExpression={expressions.input}
              />
            </div>
            <div>
              <Inputs
                onChange={handleInputChange}
                value={newCar.address}
                name="address"
                type="text"
                label="Dirección"
                errorLegend="Ingrese la dirección de entrega del vehículo."
                regularExpression={expressions.input}
              />
            </div>
          </div>
          <div className={styles.form_second_section}>
            <div>
              <label className={styles.label}>Categoría</label>
              <CategoryPicker handleCategoryPicker={handleCategoryPicker} />
            </div>
            <div className={styles.input_city}>
              <label className={styles.label}>Ciudad</label>
              <SearchCityBar handleFilterCity={handleFilterCity} />
            </div>
          </div>
        </div>
        <div>
          <label className={styles.label}>Descripción</label>
          <textarea
            className={styles.input_textarea}
            onChange={handleInputChange}
            value={newCar.description}
            id="descripcion"
            name="description"
            rows="4"
            placeholder="Escribir aquí"
          />
        </div>
        <section>
          <h2 className={styles.title}>Agregar atributos</h2>
          {newCarFeatures.length === 0 && submit && (
            <p className={styles.error_message}>
              *Agregue al menos una característica del vehículo.
            </p>
          )}
          <AddFeatures handleAddNewFeatures={handleAddNewFeatures} />
        </section>
        <section className={styles.features}>
          <h2 className={styles.title}>Cargar imágenes</h2>
          {newCarImages.length <= 4 && submit && (
            <p className={styles.error_message}>
              *Agregue al menos 5 imagenes del vehículo.
            </p>
          )}
          <AddImages handleAddNewImages={handleAddNewImages} />
        </section>
        <div className={styles.btn_container}>
          <Button
            type="submit"
            title="Crear"
            btnStyle="btn--full"
            btnSize="btn--responsive"
            onClick={onSubmit}
          />
        </div>
      </form>
    </div>
  );
}

export default Administration;
