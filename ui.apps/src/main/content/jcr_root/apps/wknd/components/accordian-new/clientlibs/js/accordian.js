
// Keeps aria-expanded in sync and optionally enforces single-open behavior.
(function () {
  const singleOpen = false; // <- set true to allow only one open at a time

  const items = Array.from(document.querySelectorAll('.accordion__item'));

  // Initialize ARIA attributes based on the open state
  items.forEach((detail, index) => {
    const summary = detail.querySelector('summary');

    // make sure the summary is keyboard accessible (it is natively)
    // and set a controllable aria-expanded attribute for assistive tech clarity
    const setExpanded = () => {
      const expanded = detail.hasAttribute('open');
      summary.setAttribute('aria-expanded', expanded ? 'true' : 'false');
    };

    // initial set
    setExpanded();

    // when toggle happens (user click or keyboard), update aria-expanded
    detail.addEventListener('toggle', () => {
      setExpanded();

      if (singleOpen && detail.hasAttribute('open')) {
        // close others
        items.forEach((otherDetail) => {
          if (otherDetail !== detail && otherDetail.hasAttribute('open')) {
            otherDetail.removeAttribute('open');
            const otherSummary = otherDetail.querySelector('summary');
            if (otherSummary) otherSummary.setAttribute('aria-expanded', 'false');
          }
        });
      }
    });

    // Optional: improve keyboard behavior consistency across older browsers:
    // Enter and Space toggle summary by default; this ensures no accidental form submission.
    summary.addEventListener('keydown', (e) => {
      if (e.key === 'Enter' || e.key === ' ') {
        // prevent default to avoid page scrolling on Space
        e.preventDefault();
        // toggle the details
        if (detail.hasAttribute('open')) detail.removeAttribute('open');
        else detail.setAttribute('open', '');
        // focus remains on summary
      }
    });
  });
})();

// Keeps aria-expanded in sync and optionally enforces single-open behavior.
(function () {
  const singleOpen = false; // <- set true to allow only one open at a time

  const items = Array.from(document.querySelectorAll('.accordion__item'));

  // Initialize ARIA attributes based on the open state
  items.forEach((detail, index) => {
    const summary = detail.querySelector('summary');

    // make sure the summary is keyboard accessible (it is natively)
    // and set a controllable aria-expanded attribute for assistive tech clarity
    const setExpanded = () => {
      const expanded = detail.hasAttribute('open');
      summary.setAttribute('aria-expanded', expanded ? 'true' : 'false');
    };

    // initial set
    setExpanded();

    // when toggle happens (user click or keyboard), update aria-expanded
    detail.addEventListener('toggle', () => {
      setExpanded();

      if (singleOpen && detail.hasAttribute('open')) {
        // close others
        items.forEach((otherDetail) => {
          if (otherDetail !== detail && otherDetail.hasAttribute('open')) {
            otherDetail.removeAttribute('open');
            const otherSummary = otherDetail.querySelector('summary');
            if (otherSummary) otherSummary.setAttribute('aria-expanded', 'false');
          }
        });
      }
    });

    // Optional: improve keyboard behavior consistency across older browsers:
    // Enter and Space toggle summary by default; this ensures no accidental form submission.
    summary.addEventListener('keydown', (e) => {
      if (e.key === 'Enter' || e.key === ' ') {
        // prevent default to avoid page scrolling on Space
        e.preventDefault();
        // toggle the details
        if (detail.hasAttribute('open')) detail.removeAttribute('open');
        else detail.setAttribute('open', '');
        // focus remains on summary
      }
    });
  });
})();

// Keeps aria-expanded in sync and optionally enforces single-open behavior.
(function () {
  const singleOpen = false; // <- set true to allow only one open at a time

  const items = Array.from(document.querySelectorAll('.accordion__item'));

  // Initialize ARIA attributes based on the open state
  items.forEach((detail, index) => {
    const summary = detail.querySelector('summary');

    // make sure the summary is keyboard accessible (it is natively)
    // and set a controllable aria-expanded attribute for assistive tech clarity
    const setExpanded = () => {
      const expanded = detail.hasAttribute('open');
      summary.setAttribute('aria-expanded', expanded ? 'true' : 'false');
    };

    // initial set
    setExpanded();

    // when toggle happens (user click or keyboard), update aria-expanded
    detail.addEventListener('toggle', () => {
      setExpanded();

      if (singleOpen && detail.hasAttribute('open')) {
        // close others
        items.forEach((otherDetail) => {
          if (otherDetail !== detail && otherDetail.hasAttribute('open')) {
            otherDetail.removeAttribute('open');
            const otherSummary = otherDetail.querySelector('summary');
            if (otherSummary) otherSummary.setAttribute('aria-expanded', 'false');
          }
        });
      }
    });

    // Optional: improve keyboard behavior consistency across older browsers:
    // Enter and Space toggle summary by default; this ensures no accidental form submission.
    summary.addEventListener('keydown', (e) => {
      if (e.key === 'Enter' || e.key === ' ') {
        // prevent default to avoid page scrolling on Space
        e.preventDefault();
        // toggle the details
        if (detail.hasAttribute('open')) detail.removeAttribute('open');
        else detail.setAttribute('open', '');
        // focus remains on summary
      }
    });
  });
})();

// Keeps aria-expanded in sync and optionally enforces single-open behavior.
(function () {
  const singleOpen = false; // <- set true to allow only one open at a time

  const items = Array.from(document.querySelectorAll('.accordion__item'));

  // Initialize ARIA attributes based on the open state
  items.forEach((detail, index) => {
    const summary = detail.querySelector('summary');

    // make sure the summary is keyboard accessible (it is natively)
    // and set a controllable aria-expanded attribute for assistive tech clarity
    const setExpanded = () => {
      const expanded = detail.hasAttribute('open');
      summary.setAttribute('aria-expanded', expanded ? 'true' : 'false');
    };

    // initial set
    setExpanded();

    // when toggle happens (user click or keyboard), update aria-expanded
    detail.addEventListener('toggle', () => {
      setExpanded();

      if (singleOpen && detail.hasAttribute('open')) {
        // close others
        items.forEach((otherDetail) => {
          if (otherDetail !== detail && otherDetail.hasAttribute('open')) {
            otherDetail.removeAttribute('open');
            const otherSummary = otherDetail.querySelector('summary');
            if (otherSummary) otherSummary.setAttribute('aria-expanded', 'false');
          }
        });
      }
    });

    // Optional: improve keyboard behavior consistency across older browsers:
    // Enter and Space toggle summary by default; this ensures no accidental form submission.
    summary.addEventListener('keydown', (e) => {
      if (e.key === 'Enter' || e.key === ' ') {
        // prevent default to avoid page scrolling on Space
        e.preventDefault();
        // toggle the details
        if (detail.hasAttribute('open')) detail.removeAttribute('open');
        else detail.setAttribute('open', '');
        // focus remains on summary
      }
    });
  });
})();

// Keeps aria-expanded in sync and optionally enforces single-open behavior.
(function () {
  const singleOpen = false; // <- set true to allow only one open at a time

  const items = Array.from(document.querySelectorAll('.accordion__item'));

  // Initialize ARIA attributes based on the open state
  items.forEach((detail, index) => {
    const summary = detail.querySelector('summary');

    // make sure the summary is keyboard accessible (it is natively)
    // and set a controllable aria-expanded attribute for assistive tech clarity
    const setExpanded = () => {
      const expanded = detail.hasAttribute('open');
      summary.setAttribute('aria-expanded', expanded ? 'true' : 'false');
    };

    // initial set
    setExpanded();

    // when toggle happens (user click or keyboard), update aria-expanded
    detail.addEventListener('toggle', () => {
      setExpanded();

      if (singleOpen && detail.hasAttribute('open')) {
        // close others
        items.forEach((otherDetail) => {
          if (otherDetail !== detail && otherDetail.hasAttribute('open')) {
            otherDetail.removeAttribute('open');
            const otherSummary = otherDetail.querySelector('summary');
            if (otherSummary) otherSummary.setAttribute('aria-expanded', 'false');
          }
        });
      }
    });

    // Optional: improve keyboard behavior consistency across older browsers:
    // Enter and Space toggle summary by default; this ensures no accidental form submission.
    summary.addEventListener('keydown', (e) => {
      if (e.key === 'Enter' || e.key === ' ') {
        // prevent default to avoid page scrolling on Space
        e.preventDefault();
        // toggle the details
        if (detail.hasAttribute('open')) detail.removeAttribute('open');
        else detail.setAttribute('open', '');
        // focus remains on summary
      }
    });
  });
})();

// Keeps aria-expanded in sync and optionally enforces single-open behavior.
(function () {
  const singleOpen = false; // <- set true to allow only one open at a time

  const items = Array.from(document.querySelectorAll('.accordion__item'));

  // Initialize ARIA attributes based on the open state
  items.forEach((detail, index) => {
    const summary = detail.querySelector('summary');

    // make sure the summary is keyboard accessible (it is natively)
    // and set a controllable aria-expanded attribute for assistive tech clarity
    const setExpanded = () => {
      const expanded = detail.hasAttribute('open');
      summary.setAttribute('aria-expanded', expanded ? 'true' : 'false');
    };

    // initial set
    setExpanded();

    // when toggle happens (user click or keyboard), update aria-expanded
    detail.addEventListener('toggle', () => {
      setExpanded();

      if (singleOpen && detail.hasAttribute('open')) {
        // close others
        items.forEach((otherDetail) => {
          if (otherDetail !== detail && otherDetail.hasAttribute('open')) {
            otherDetail.removeAttribute('open');
            const otherSummary = otherDetail.querySelector('summary');
            if (otherSummary) otherSummary.setAttribute('aria-expanded', 'false');
          }
        });
      }
    });

    // Optional: improve keyboard behavior consistency across older browsers:
    // Enter and Space toggle summary by default; this ensures no accidental form submission.
    summary.addEventListener('keydown', (e) => {
      if (e.key === 'Enter' || e.key === ' ') {
        // prevent default to avoid page scrolling on Space
        e.preventDefault();
        // toggle the details
        if (detail.hasAttribute('open')) detail.removeAttribute('open');
        else detail.setAttribute('open', '');
        // focus remains on summary
      }
    });
  });
})();

// Keeps aria-expanded in sync and optionally enforces single-open behavior.
(function () {
  const singleOpen = false; // <- set true to allow only one open at a time

  const items = Array.from(document.querySelectorAll('.accordion__item'));

  // Initialize ARIA attributes based on the open state
  items.forEach((detail, index) => {
    const summary = detail.querySelector('summary');

    // make sure the summary is keyboard accessible (it is natively)
    // and set a controllable aria-expanded attribute for assistive tech clarity
    const setExpanded = () => {
      const expanded = detail.hasAttribute('open');
      summary.setAttribute('aria-expanded', expanded ? 'true' : 'false');
    };

    // initial set
    setExpanded();

    // when toggle happens (user click or keyboard), update aria-expanded
    detail.addEventListener('toggle', () => {
      setExpanded();

      if (singleOpen && detail.hasAttribute('open')) {
        // close others
        items.forEach((otherDetail) => {
          if (otherDetail !== detail && otherDetail.hasAttribute('open')) {
            otherDetail.removeAttribute('open');
            const otherSummary = otherDetail.querySelector('summary');
            if (otherSummary) otherSummary.setAttribute('aria-expanded', 'false');
          }
        });
      }
    });

    // Optional: improve keyboard behavior consistency across older browsers:
    // Enter and Space toggle summary by default; this ensures no accidental form submission.
    summary.addEventListener('keydown', (e) => {
      if (e.key === 'Enter' || e.key === ' ') {
        // prevent default to avoid page scrolling on Space
        e.preventDefault();
        // toggle the details
        if (detail.hasAttribute('open')) detail.removeAttribute('open');
        else detail.setAttribute('open', '');
        // focus remains on summary
      }
    });
  });
})();

// Keeps aria-expanded in sync and optionally enforces single-open behavior.
(function () {
  const singleOpen = false; // <- set true to allow only one open at a time

  const items = Array.from(document.querySelectorAll('.accordion__item'));

  // Initialize ARIA attributes based on the open state
  items.forEach((detail, index) => {
    const summary = detail.querySelector('summary');

    // make sure the summary is keyboard accessible (it is natively)
    // and set a controllable aria-expanded attribute for assistive tech clarity
    const setExpanded = () => {
      const expanded = detail.hasAttribute('open');
      summary.setAttribute('aria-expanded', expanded ? 'true' : 'false');
    };

    // initial set
    setExpanded();

    // when toggle happens (user click or keyboard), update aria-expanded
    detail.addEventListener('toggle', () => {
      setExpanded();

      if (singleOpen && detail.hasAttribute('open')) {
        // close others
        items.forEach((otherDetail) => {
          if (otherDetail !== detail && otherDetail.hasAttribute('open')) {
            otherDetail.removeAttribute('open');
            const otherSummary = otherDetail.querySelector('summary');
            if (otherSummary) otherSummary.setAttribute('aria-expanded', 'false');
          }
        });
      }
    });

    // Optional: improve keyboard behavior consistency across older browsers:
    // Enter and Space toggle summary by default; this ensures no accidental form submission.
    summary.addEventListener('keydown', (e) => {
      if (e.key === 'Enter' || e.key === ' ') {
        // prevent default to avoid page scrolling on Space
        e.preventDefault();
        // toggle the details
        if (detail.hasAttribute('open')) detail.removeAttribute('open');
        else detail.setAttribute('open', '');
        // focus remains on summary
      }
    });
  });
})();
