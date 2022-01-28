import React, { useState, useEffect } from "react";
import styles from "./AddImages.module.css";
import Swal from "sweetalert2";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faPlus,
  faTimes,
  faArrowUp,
  faArrowDown,
} from "@fortawesome/free-solid-svg-icons";
import Inputs from "../Inputs/Inputs";

function AddImages(props) {
  const [listImages, setListImages] = useState(null);
  const [validation, setValidation] = useState(false);
  const [newCarImages, setNewCarImages] = useState([]);
  const [newCarImage, setNewCarImage] = useState({
    title: "",
    url: "",
    position: null,
  });
  const [imageValidation, setImageValidation] = useState({
    title: false,
    url: false,
  });
  const expressions = {
    title: /^[a-zA-Z0-9_.+-:", ]+$/,
    url: /(^.)/,
  };

  useEffect(() => {
    props.handleAddNewImages([...newCarImages]);
  }, [newCarImages]);

  const handleInputImageChange = (e) => {
    const target = e.target;
    const value = target.value;
    const name = target.name;

    setNewCarImage({
      ...newCarImage,
      [name]: value,
    });
  };
  const handleAddImages = () => {
    if (newCarImage.title === "" || newCarImage.url === "") {
      setValidation(true);
    } else {
      const pos = newCarImages.length + 1;
      setValidation(false);
      setNewCarImages([...newCarImages, { ...newCarImage, position: pos }]);
      setNewCarImage({
        title: "",
        url: "",
        position: null,
      });
    }
  };

  useEffect(() => {
    const handleDeleteImage = (idImage) => {
      Swal.fire({
        title: "¿Desea borrar la imagen?",
        icon: "warning",
        showCancelButton: true,
        background: "#f3f3f3",
        confirmButtonColor: "#b40505",
        cancelButtonColor: "#000000",
        confirmButtonText: "Borrar",
        cancelButtonText: "No borrar",
      }).then((result) => {
        if (result.isConfirmed) {
          const array = [...newCarImages];
          array.splice(idImage, 1);
          setNewCarImages([...array]);
        }
      });
    };

    const handlePositionDown = (idImage) => {
      const newIdImage = parseInt(idImage) + 1;
      const list = [...newCarImages];
      const elementMooved = list.splice(idImage, 1);
      list.splice(newIdImage, 0, ...elementMooved);
      const arrayImages = [];
      list.map((element, index) => {
        arrayImages.push({ ...element, position: index + 1 });
        return arrayImages;
      });
      setNewCarImages([...arrayImages]);
    };

    const handlePositionUp = (idImage) => {
      const newIdImage = idImage - 1;
      const list = [...newCarImages];
      const elementMooved = list.splice(idImage, 1);
      list.splice(newIdImage, 0, ...elementMooved);
      const arrayImages = [];
      list.map((element, index) => {
        arrayImages.push({ ...element, position: index + 1 });
        return arrayImages;
      });
      setNewCarImages([...arrayImages]);
    };

    const listImage = () =>
      newCarImages.map((carImage, index) => {
        return (
          <div
            key={`${carImage.title}_${index}`}
            className={styles.new_image_box}
            id={index}
          >
            <div className={styles.new_image}>
              <input
                className={styles.input}
                type="text"
                value={carImage.url}
                disabled
              />
              <input
                className={styles.input}
                type="text"
                value={carImage.title}
                disabled
              />
            </div>
            <div className={styles.btn_container_new_image}>
              <div className={styles.position}>
                <button
                  className={styles.icon_position_up}
                  type="button"
                  id={index}
                  onClick={(e) => handlePositionUp(e.target.id)}
                >
                  {index === 0 && (
                    <FontAwesomeIcon
                      className={styles.icon_position_disabled}
                      icon={faArrowUp}
                    />
                  )}
                  {index > 0 && (
                    <FontAwesomeIcon
                      className={styles.icon_position}
                      icon={faArrowUp}
                      id={index}
                      onClick={(e) => handlePositionUp(e.target.id)}
                    />
                  )}
                </button>
                <button
                  className={styles.icon_position_down}
                  type="button"
                  id={index}
                  onClick={(e) => handlePositionDown(e.target.id)}
                >
                  {index === newCarImages.length - 1 && (
                    <FontAwesomeIcon
                      className={styles.icon_position_disabled}
                      icon={faArrowDown}
                    />
                  )}
                  {index < newCarImages.length - 1 && (
                    <FontAwesomeIcon
                      className={styles.icon_position}
                      icon={faArrowDown}
                      id={index}
                      onClick={(e) => handlePositionDown(e.target.id)}
                    />
                  )}
                </button>
              </div>
              <button
                className={styles.btn_delete_image}
                type="button"
                id={index}
                onClick={(e) => handleDeleteImage(e)}
              >
                <FontAwesomeIcon icon={faTimes} />
              </button>
            </div>
          </div>
        );
      });
    setListImages(listImage);
  }, [newCarImages]);

  return (
    <div className={styles.images}>
      <div className={styles.new_images}>
        {newCarImages.length > 0 && <div>{listImages}</div>}
      </div>

      <div className={styles.add_images_section}>
        <div className={styles.add_images}>
          <Inputs
            label="URL imágen"
            className={styles.input_image_url}
            onChange={handleInputImageChange}
            value={newCarImage.url}
            name="url"
            type="text"
            id="image_url"
            placeholder="Insertar http://"
            errorLegend="Insertar URL de la imagen http://"
            regularExpression={expressions.url}
            validState={imageValidation}
            setValidState={setImageValidation}
          />
          <Inputs
            label="Título"
            onChange={handleInputImageChange}
            value={newCarImage.title}
            name="title"
            type="text"
            id="image_url"
            placeholder="Titulo imagen"
            errorLegend="Ingrese el titulo de la imagen"
            regularExpression={expressions.title}
            validState={imageValidation}
            setValidState={setImageValidation}
          />
        </div>
        <div className={styles.btn_container}>
          <button
            className={styles.btn_add_image}
            type="button"
            onClick={handleAddImages}
          >
            <FontAwesomeIcon icon={faPlus} />
          </button>
        </div>
      </div>
    </div>
  );
}

export default AddImages;
