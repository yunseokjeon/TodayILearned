```CSS
#about {
    background-color: var(--color-light-bg);
}

.about {
    display: 
    gap: 1em;
    margin-top: 48px;
}

.about__card {
    position: 
    padding: 48px;
    text-align: 
    color: white;
    background-color: var(--color-dark);
    border-radius: 12px;
}
.about__icon {
    width: 120px;
    opacity: 0.25;
}

.about__title {
    margin-top: 0.6em;
    font-size: var(--font-size-larger);
    font-weight: bold;
}

.about__title._1 {
    color: #FFB974;
}
.about__title._2 {
    color: #6BB0F3;
}
.about__title._3 {
    color: #FFBDCE;
}

.about__text {
    margin-top: 0.8em;
    font-size: var(--font-size-text);
}

@media (max-width: 768px) {

    .about {
        flex-direction: 
        width: 100%;
    }

}

@media (min-width: 769px) {
    
    #about {
        grid-column: 
    }

    .about__card {
        width: 320px;
        cursor: pointer;
        transition: all 350ms;
    }
    
    .about:hover .about__card:not(:hover) {
        transform: 
    }
    
    .about:hover .about__card:not(:hover)::after {
        content: '';
        position: 
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        -webkit-backdrop-filter:
        backdrop-filter:
    }

    .about__card:hover {
        transform: 
    }
    .about__card:hover .about__icon {
        opacity: 
    }

}
```