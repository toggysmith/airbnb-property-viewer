# <img src=".github/assets/banner.svg" width="100%" />

Airbnb property viewer is a desktop GUI for viewing Airbnb properties in London, built as part of an assignment for 4CCS2PPA at King's College London. The main application was made using Java and [JavaFX](https://openjfx.io/), but there is a smaller portion which relies on HTML, CSS, JS, and the [OpenLayers 3](https://openlayers.org/) library. It was required that we use [BlueJ](https://www.bluej.org/) when developing the project so it is only possible to launch the application from within the BlueJ IDE.

# Features

1. **Static UI elements:** There are some UI elements which remain fixed on the screen regardless of which sub-screen you are on. One of these is a price range selector which enables the user to select a price range they want to limit their property search to. Initially, this went up in fixed intervals but we changed it so it went up in increasing intervals. E.g. it goes up in 10's before you reach 100 and then after that it goes up in 100's until you reach 1000 and then it goes up in 1000's.
1. **Welcome screen:** This shows a welcome message along with some helpful advice on how to use the application. There are also buttons to toggle the color mode and open up the GitHub repository for the project in the user's default browser. Originally, this project was hosted on GitHub Enterprise so the private GitHub repository it links to is not this one and should not be accessible when it opens in your browser.
2. **Abstract borough map:** Boroughs are represented as hexagons with house icons belong them. More houses means that a borough has more properties relative to the other boroughs. These hexagon icons are clickable and will open up a screen with a list of properties for that borough.
3. **Statistics screen:** This consists of four panels which cycle through a list of potential statistics. Two panels cannot show the same statistics at the same time. This screen is honestly a bit strange in terms of UX but it was a requirement of the assignment. We implemented it using a circular list and we skip a statistic if it is already shown on another panel. For the actual statistics, we provided useful stats based on the Airbnb properties dataset we were given along with other datasets we found online. This enabled us to combine the two datasets to e.g. show the closest pubs or tourist attractions to a property.
4. **Real-world map:** This screen shows a real-world map. In terms of technical details, this was created by embedding a local website within a JavaFX WebView component. The local website uses regular HTML/CSS/JS along with the OpenLayers 3 map library to show the map. We were inspired by Rightmove's draw-a-search functionality: users can draw a search area over the real-world map in order to limit their property search area. All of the property markers are interactive which means when clicked on it will open up a screen with more details about the property.

# Getting Started

## Prerequisites

- [Git](https://git-scm.com/).
- [BlueJ](https://www.bluej.org/).

## Cloning

Clone this repository to your machine:

```bash
git clone https://github.com/toggysmith/airbnb-property-viewer
```

## Running the Application

Open the newly created `airbnb-property-viewer` directory in BlueJ. Locate the `App` class and right-click on it. A menu should open allowing you to execute it as a JavaFX application.

## Running Tests

Within BlueJ, there should be a `Tools` tab which will open to reveal a `Testing` tab which will also open to reveal a `Run Tests` button.

<details>
  <summary>A screenshot of some tests running.</summary>
  <img src="https://github.com/toggysmith/airbnb-property-viewer/assets/61121030/6d411e2f-061d-4393-b944-36c843097737" />
</details>

# Walkthrough

https://github.com/toggysmith/airbnb-property-viewer/assets/61121030/19e75b6f-c4ff-4c87-afe5-efeb65c0ad99

# Screenshots

<details>
  <summary>The main screen in light mode.</summary>
  <img src="https://github.com/toggysmith/airbnb-property-viewer/assets/61121030/a8c6032e-b87b-4345-ac2a-e7b3f90c4fb4" />
</details>

<details>
  <summary>The main screen in dark mode.</summary>
  <img src="https://github.com/toggysmith/airbnb-property-viewer/assets/61121030/d982bf9c-4267-4062-9ae2-4f7003cddf08" />
</details>

<details>
  <summary>An abstract map of the boroughs.</summary>
  <img src="https://github.com/toggysmith/airbnb-property-viewer/assets/61121030/22d4a87b-85f9-4290-8c1c-569aa42017b7" />
  <p>More of the house icons indicates that a borough has more properties relative to the rest.</p>
</details>

<details>
  <summary>A screen for showing different statistics.</summary>
  <img src="https://github.com/toggysmith/airbnb-property-viewer/assets/61121030/a72ffbc2-12ee-4c77-90d0-7fa735e1b870" />
</details>

<details>
  <summary>A screen showing a real-world map of the boroughs.</summary>
  <img src="https://github.com/toggysmith/airbnb-property-viewer/assets/61121030/91e4c3e6-3bdf-455f-8542-8f3779c7228b" />
</details>

<details>
  <summary>A demonstration of the draw-an-area functionality on the real-world map screen.</summary>
  <img src="https://github.com/toggysmith/airbnb-property-viewer/assets/61121030/6ce2dcf0-6109-4a4d-8e5d-62e8ea120ef8" />
  <img src="https://github.com/toggysmith/airbnb-property-viewer/assets/61121030/5266c951-b252-4f9e-b99b-adfd9abb567b" />
  <img src="https://github.com/toggysmith/airbnb-property-viewer/assets/61121030/2a1ee515-c04c-4dfa-8894-0e891b9d0847" />
  <img src="https://github.com/toggysmith/airbnb-property-viewer/assets/61121030/c5a0d058-ef31-4e16-bc2e-ca0aeeed41c7" />
</details>

<details>
  <summary>An example of the details that might be shown about a property.</summary>
  <img src="https://github.com/toggysmith/airbnb-property-viewer/assets/61121030/14f8c622-1e63-482a-a97d-9188b19b5175" />
</details>

# Contributors
- [Adam Murray](https://github.com/AdamMurray22)
- [Augusto Favero](https://github.com/AFaverooo)
- [Mathew Tran](https://github.com/pluc0)
- [Toggy Smith](https://github.com/toggysmith)

<a href="https://toggysmith.com">
  <picture>
    <source srcset="https://user-images.githubusercontent.com/61121030/234412163-6027c7f8-ffbe-4ebb-b83b-c8ce1941c5b4.png" media="(prefers-color-scheme: dark)" />
    <source srcset="https://user-images.githubusercontent.com/61121030/234409401-6c9037df-566d-4649-a5cc-12782ada38b5.png" media="(prefers-color-scheme: light)" />
    <img src="https://user-images.githubusercontent.com/61121030/234409401-6c9037df-566d-4649-a5cc-12782ada38b5.png" width="200" align="right" />
  </picture>
</a>
