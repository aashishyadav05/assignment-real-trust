function scrollCardsLeft() {
  const container = document.getElementById('cardContainer');
  container.scrollBy({ left: -264, behavior: 'smooth' });
}

function scrollCardsRight() {
  const container = document.getElementById('cardContainer');
  container.scrollBy({ left: 264, behavior: 'smooth' });
}
