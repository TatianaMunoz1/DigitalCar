import React, { useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHeart } from "@fortawesome/free-solid-svg-icons";
import { faHeart as farFaHeart } from "@fortawesome/free-regular-svg-icons";
import styles from "./FavoriteHeart.module.css";

function FavoriteHeart(props) {
  const [favorites, setFavorites] = useState([]);
  const [favClicked, setFavClicked] = useState(false);

  const handleFav = (idFav) => {
    const array = [...favorites];
    const index = array.indexOf(idFav);
    if (index !== -1) {
      array.splice(index, 1);
      setFavorites([...array]);
    } else {
      array.push(idFav);
      setFavorites([...array]);
    }
  };

  return (
    <FontAwesomeIcon
      icon={favClicked ? faHeart : farFaHeart}
      id={props.id}
      className={styles.icon_heart}
      onClick={() => {
        handleFav(props.id);
        setFavClicked(!favClicked);
      }}
      styles={{ color: favClicked ? "red" : "" }}
    />
  );
}

export default FavoriteHeart;
