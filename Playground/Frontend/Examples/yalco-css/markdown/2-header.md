.header {
    display: 
    position: 

    z-index: 
    top: 
    height: var(--height-toolbar);
    justify-content: 
    align-items: 
    background-color: var(--color-main);

    color: white;
}

.header__homelink {
    display:
    padding: 
    height: var(--height-toolbar);
    line-height: var(--height-toolbar);
    cursor: pointer;
}
.header__logo {
    height: 48px;
    vertical-align: 
    margin-bottom: 4px;
}

.header__nav-item {
    font-size: var(--font-size-larger);
}

.header__nav-item a {
    display: 
    height: var(--height-toolbar);
    line-height: var(--height-toolbar);
}

@media (max-width: 768px) {

    .header__nav {
        display: 
    }
    .header__menu-btn {
        all: 
        display: 
        width: var(--height-toolbar);
        height: var(--height-toolbar);
        background-image: url(../images/menu-button.svg);
        background-size: 
        background-repeat: 
        background-position: 
    }
    .header__menu-btn:checked {
        background-color: var(--color-dark);
    }
    .header__menu-btn:checked + .header__nav {
        display: 
        position: 
        top: var(--height-toolbar);
        right: 
        background-color: var(--color-dark);
        padding-bottom: 
        box-shadow: 
    }
    .header__nav-item {
        width: 
        text-align:
    }
    .header__nav-item a {
        width: 
    }
    .header__nav-item:not(:last-child) {
        border-bottom: 2px solid rgba(255, 255, 255, 0.06);
    }
}

@media (min-width: 769px) {
    .header__nav-item {
        display: 
        position: 
    }
    .header__nav-item:last-child {
        margin-right: 1.6em;
    }

    .header__nav-item a {
        padding: 0 0.8em;
    }

    .header__nav-item::after {
        content: 
        position: 
        bottom: 
        left: 
        width: 
        height:
        background-color: 
        transition: 
    }
    .header__nav-item:hover::after {
        left: 
        width: 
        height: 
    }

    .header__menu-btn {
        display: 
    }
}