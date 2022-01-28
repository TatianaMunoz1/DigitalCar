import React, { useState } from "react";
import ImageGallery from "react-image-gallery";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTimes } from "@fortawesome/free-solid-svg-icons";
import "react-image-gallery/styles/css/image-gallery.css";
import "./Gallery.css";

function Gallery(props) {
  const [showFullScreen, setShowFullScreen] = useState(false);
  const [carImages, setCarImages] = useState({ ...props });

  const itemsList = carImages.images.map((img) => {
    return {
      id: img.id,
      original: img.url,
      thumbnail: img.url,
    };
  });

  return (
    <>
      <div className="gallery_detail_phone_and_tablet">
        <ImageGallery
          items={itemsList}
          showThumbnails={false}
          showPlayButton={false}
          showIndex={true}
          showFullscreenButton={false}
          slideDuration={2000}
          slideInterval={3000}
          autoPlay={true}
        />
      </div>
      <div className="gallery_detail_desktop">
        {carImages.images.slice(0, 5).map((image, index) => {
          let position = index + 1;
          return (
            <div
              className={`image_container_detail_${position}`}
              key={`${image.id}_${image.title}`}
            >
              <img
                src={image.url}
                alt={image.title}
                className={`image_detail_${position}`}
              />
            </div>
          );
        })}

        <button
          className="gallery_btn_detail_desktop"
          onClick={() => setShowFullScreen(true)}
        >
          Ver más
        </button>
        {showFullScreen && (
          <div className="gallery_detail_desktop_fullscreen">
            <div className="image_background_desktop_fullscreen">
              <div className="image_detail_desktop_fullscreen">
                <ImageGallery
                  items={itemsList}
                  showThumbnails={true}
                  showPlayButton={false}
                  showIndex={true}
                  showBullets={true}
                  showFullscreenButton={false}
                />
                <FontAwesomeIcon
                  className="close_icon_fullscreen_gallery_detail"
                  icon={faTimes}
                  onClick={() => setShowFullScreen(false)}
                />
              </div>
            </div>
          </div>
        )}
      </div>
    </>
  );
}

export default Gallery;
