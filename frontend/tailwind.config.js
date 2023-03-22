/** @type {import('tailwindcss').Config} */

module.exports = {
  content: ['./pages/**/*.{js,ts,jsx,tsx}', './components/**/*.{js,ts,jsx,tsx}'],
  theme: {
    extend: {
      colors: {
        // primary, secondary color
        PrimaryLight: 'rgba(190, 52, 85, 1)', // Magenta-Light1
        SecondaryLight: 'rgba(219, 98, 146, 1)', // Magenta-Light4
        SecondaryVariantLight: 'rgba(219, 98, 146, 0.8)', // Magenta-Light4 80%
        PrimaryDark: 'rgba(150, 70, 119, 1)', // Magenta-Dark1
        SecondaryDark: 'rgba(106, 105, 143, 1)', // Magenta-Dark2
        SecondaryVariantDark: 'rgba(138, 111, 121, 1)', // Magenta-Dark3

        // Background, Component color
        BackgroundDark: 'rgba(18, 18, 18, 1)', // Background-Dark
        BackgroundDarkComponent: 'rgba(37, 37, 37, 1)', // Background-Dark-Component
        BackgroundLight: 'rgba(243, 243, 243, 1)', // Background-Light
        BackgroundLightComponent: 'rgba(255, 255, 255, 1)',  // Background-Light-Component
        BackgroundLightComponentBorder: 'rgba(234, 234, 234, 1)', // Background-Light-Component-bolder
        BackgroundDarkComponentBorder: 'rgba(47, 47, 47, 1)', // Background-Light-Component-bolder

        // Font color
        FontPrimaryLight: 'rgba(0, 0, 0, 1)', // Font-Light
        FontSecondaryLight: 'rgba(0, 0, 0, 0.6)', // Font-sub-Light
        FontPrimaryDark: 'rgba(255, 255, 255, 0.92)', // Font-Dark
        FontSecondaryDark: 'rgba(255, 255, 255, 0.6)', // Font-sub-Dark

        // other color
        TaskBarBackground: 'rgba(224, 224, 224, 1)', // taskbar-background
        FooterIcon: 'rgba(55, 73, 87, 1)', // footer-icon
        FooterBackground: 'rgba(240, 240, 240, 1)', // footer-background
        ImageCover70: 'rgba(0, 0, 0, 0.7)', // Image-cover-70
        ImageCover30: 'rgba(0, 0, 0, 0.3)', // Image-cover-30
        CommentBackground: 'rgba(255, 255, 255, 0.3)',
      },
    },
  },
  plugins: [require('@tailwindcss/line-clamp')],
};
