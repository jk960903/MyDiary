import 'core-js/stable';
import 'regenerator-runtime/runtime';

import 'react-app-polyfill/ie11';
import 'react-app-polyfill/stable';
import 'ts-polyfill';

import React, {useEffect} from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import 'bootstrap/dist/css/bootstrap.min.css';

import Routes from './router/Routes';
import Header from './component/Header';
import Footer from './component/Footer';
import reportWebVitals from './reportWebVitals';

document.title = "Title"

ReactDOM.render(
  <React.StrictMode>
    <Header />
    <Routes />
    <Footer/>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
