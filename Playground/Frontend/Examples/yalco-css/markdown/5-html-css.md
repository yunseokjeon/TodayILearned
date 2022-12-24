```CSS

.html-css {
    margin: 56px 0;
    display: 
    gap: 56px;
}

.html-css__desc {
    margin-top: 1.6em;
    font-size: var(--font-size-text);
    color: var(--color-text);
}
.html-css__desc strong {
    font-weight: bold;
}
.html-css__title {
    font-size: var(--font-size-subtitle);
    font-weight: 100;
}
._html .html-css__title em { color: #F16528; }
._css .html-css__title em { color: #2965F1; }

.html-css__spec {
    margin-top: 1.6em;
}
.html-css__spec dd {
    margin-right: 1.2em;
    font-size: var(--font-size-larger);
    color: var(--color-lighter);
}

.html-css__spec dd::before {
    content: '';
    display: 
    margin-right: 0.4em;
    width: 12px;
    height: 6px;
    border-left: 4px solid var(--color-sub);
    border-bottom: 4px solid var(--color-sub);
    vertical-align: 0.2em;
    transform: 
}

.html-css__thumb {
    position: 
}

.html-css__thumb::after {
    content: '';
    position: 
    left: 0;
    height: 10%;
    background-color: black;
    border-radius: 
}

@keyframes logo-hover {
    from { transform:  }
    to { transform:  }
}

.html-css__logo {
    animation-name: logo-hover;
    animation-duration: 800ms;
    animation-timing-function: 
    animation-iteration-count: 
    animation-direction: 
}

/* 짙고 옅어지는 애니메이션 */
@keyframes logo-shadow {
    from { opacity: 0.08; }
    to { opacity: 0.24; }
}

.html-css__thumb::after {
    animation-name: logo-shadow;
    animation-duration: 800ms;
    animation-timing-function: 
    animation-iteration-count: 
    animation-direction: 
}

@media (max-width: 768px) {
    #html {
        border-bottom: 4px solid var(--color-light-bg);
    }
    .html-css {
        flex-direction: column;
    }

    .html-css__logo {
        width: 50%;
    }

    .html-css__content {
        text-align: center;
    }
    .html-css__title div {
        display: inline-block;
        text-align: left;
    }

    .html-css__thumb::after {
        left: 25%;
        bottom: -16%;
        width: 50%;
    }
}

@media (min-width: 769px) {
    #html {
        border-right: 4px solid var(--color-light-bg);
    }

    .html-css__logo {
        width: 144px;
    }

    .html-css__content {
        text-align: left;
    }

    .html-css__thumb::after {
        left: calc(50% - 72px);
        bottom: -24px;
        width: 144px;
    }
}
```