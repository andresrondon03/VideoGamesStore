import { defineCollection, z } from "astro:content";
import { glob } from "astro/loaders";

const home = defineCollection({
  loader: glob({ pattern: "**/*.md", base: "./src/content/home" }),
  schema: z.object({
    title: z.string(),
    subtitle: z.string(),
    ctaLabel: z.string(),
    ctaHref: z.string(),
    featuredCategories: z.array(z.string()),
  }),
});

const marketing = defineCollection({
  loader: glob({ pattern: "**/*.md", base: "./src/content/marketing" }),
  schema: z.object({
    title: z.string(),
    description: z.string(),
  }),
});

export const collections = { home, marketing };
