// @ts-check
// Note: type annotations allow type checking and IDEs autocompletion

const lightCodeTheme = require('prism-react-renderer/themes/github');
const darkCodeTheme = require('prism-react-renderer/themes/dracula');
const math = require('remark-math')

async function createConfig() {
  const katex = (await import('rehype-katex')).default
  /** @type {import('@docusaurus/types').Config} */
  return {
    title: 'Pulverization',
    tagline: 'Pulverization framework',
    url: 'https://nicolasfarabegoli.it',
    baseUrl: '/pulverization-framework/',
    onBrokenLinks: 'throw',
    onBrokenMarkdownLinks: 'warn',
    favicon: 'img/favicon.ico',

    // GitHub pages deployment config.
    // If you aren't using GitHub pages, you don't need these.
    organizationName: 'nicolasfara', // Usually your GitHub org/user name.
    projectName: 'pulverization-framework', // Usually your repo name.

    // Even if you don't use internalization, you can use this field to set useful
    // metadata like html lang. For example, if your site is Chinese, you may want
    // to replace "en" with "zh-Hans".
    i18n: {
      defaultLocale: 'en',
      locales: ['en'],
    },

    markdown: {
      mermaid: true,
    },
    themes: ['@docusaurus/theme-mermaid'],

    presets: [
      [
        'classic',
        /** @type {import('@docusaurus/preset-classic').Options} */
        ({
          docs: {
            remarkPlugins: [math],
            rehypePlugins: [katex],
            sidebarPath: require.resolve('./sidebars.js'),
            // Please change this to your repo.
            // Remove this to remove the "edit this page" links.
            editUrl:
              'https://github.com/nicolasfara/pulverization-framework/tree/master/docsite',
          },
          blog: {
            showReadingTime: true,
            // Please change this to your repo.
            // Remove this to remove the "edit this page" links.
            editUrl:
              'https://github.com/nicolasfara/pulverization-framework/tree/master/docsite',
          },
          theme: {
            customCss: require.resolve('./src/css/custom.css'),
          },
        }),
      ],
    ],

    themeConfig:
    /** @type {import('@docusaurus/preset-classic').ThemeConfig} */
      ({
        navbar: {
          title: 'Pulverization',
          // logo: {
          //   alt: 'Pulverization logo',
          //   src: 'img/logo.svg',
          // },
          items: [
            {
              type: 'doc',
              docId: 'intro',
              position: 'left',
              label: 'Tutorial',
            },
            {to: '/blog', label: 'Blog', position: 'left'},
            {
              href: 'https://github.com/nicolasfara/pulverization-framework',
              className: 'header-github-link',
              'aria-label': 'GitHub repository',
              position: 'right',
            },
          ],
        },
        footer: {
          style: 'dark',
          links: [
            {
              title: 'Docs',
              items: [
                {
                  label: 'Tutorial',
                  to: '/docs/intro',
                },
              ],
            },
            {
              title: 'More',
              items: [
                {
                  label: 'Blog',
                  to: '/blog',
                },
                {
                  label: 'GitHub',
                  href: 'https://github.com/nicolasfara/pulverization-framework',
                },
              ],
            },
          ],
          copyright: `Copyright © ${new Date().getFullYear()} My Project, Inc. Built with Docusaurus.`,
        },
        prism: {
          additionalLanguages: ['kotlin', 'groovy'],
          theme: lightCodeTheme,
          darkTheme: darkCodeTheme,
        },
        mermaid: {
          theme: {light: 'base', dark: 'dark'},
        },
      }),
    stylesheets: [
      {
        href: 'https://cdn.jsdelivr.net/npm/katex@0.16.3/dist/katex.min.css',
        type: 'text/css',
        integrity: 'sha384-Juol1FqnotbkyZUT5Z7gUPjQ9gzlwCENvUZTpQBAPxtusdwFLRy382PSDx5UUJ4/',
        crossorigin: 'anonymous',
      },
    ],
  }
}

module.exports = createConfig;
