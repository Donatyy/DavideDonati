function acceptPrivacy() {
  document.getElementById("privacy-banner").style.display = "none";
}

// Mostra il banner dopo 2 secondi
window.onload = function() {
  setTimeout(function() {
    document.getElementById("privacy-banner").style.display = "block";
  }, 2000);
};
